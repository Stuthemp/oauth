package stuthemp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stuthemp.message_queue.CreationPublisher;
import stuthemp.model.Question;
import stuthemp.model.QuestionType;
import stuthemp.serive.QuestionService;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Controller, provides access to methods for work questions
 */
@RestController
public class QuestionManagementController {

    private Logger logger = Logger.getLogger(QuestionManagementController.class.getName());

    private QuestionService questionService;

    private CreationPublisher creationPublisher;

    @Autowired
    public QuestionManagementController(QuestionService questionService, CreationPublisher creationPublisher) {
        this.questionService = questionService;
        this.creationPublisher = creationPublisher;
    }

    @PostMapping(value = "/add/question", headers=("content-type=multipart/*"))
    public Question addQuestion(@RequestParam MultipartFile photo, HttpServletRequest request) throws IOException {
        Question question = new Question();
        SecurityContext context = SecurityContextHolder.getContext();

        question.setRightAnswer(request.getHeader("rightAnswer"));
        question.setWrongAnswer1(request.getHeader("wrongAnswer1"));
        question.setWrongAnswer2(request.getHeader("wrongAnswer2"));
        question.setWrongAnswer3(request.getHeader("wrongAnswer3"));
        question.setQuestionType(QuestionType.get(request.getHeader("questionType")));
        question.setDocRef(request.getHeader("docRef"));

        String name = request.getHeader("name").toString();

        try {
            questionService.save(photo.getBytes(),name,question);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        creationPublisher.publishCreationInfo(question, context.getAuthentication().getName());
        return question;
    }

    @GetMapping("/questions/{type}")
    public List<Question> findQuestionsByQuestionType(@PathVariable String type) {
        QuestionType questionType = QuestionType.get(type);
        return questionService.findQuestionsByType(questionType);
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    FileSystemResource downloadQuestion(@RequestParam("id") Long id) {
        return questionService.find(id);
    }

    @GetMapping("/questions/all")
    @ResponseBody
    public List<Question> findAllQuestions() {
        return questionService.findAllQuestions();
    }

    @GetMapping("/question/{id}")
    public Question findQuestionById(@PathVariable Long id) {
        try{
            return questionService.findQuestionById(id);
        } catch (EntityNotFoundException ex) {
            logger.warning("Question not found");
        }
        return null;
    }
}
