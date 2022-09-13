package stuthemp.serive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stuthemp.model.Question;
import stuthemp.model.QuestionType;
import stuthemp.repository.FileSystemRepository;
import stuthemp.repository.QuestionRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Service class for Questions
 */
@Service
public class QuestionServiceImpl implements QuestionService{

    Logger logger = Logger.getLogger(QuestionServiceImpl.class.getName());

    QuestionRepository questionRepository;

    FileSystemRepository fileSystemRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, FileSystemRepository fileSystemRepository) {
        this.questionRepository = questionRepository;
        this.fileSystemRepository = fileSystemRepository;
    }

    @Override
    public List<Question> findQuestionsByType(QuestionType type) {
        List<Question> result = questionRepository.getQuestionsByQuestionType(type);
        if(result.isEmpty()) {
            throw new EntityExistsException("Not found questions with type: " + type);
        }
        return result;
    }

    @Transactional
    public void save(byte[] bytes, String imageName, Question question) throws Exception {
        String location = fileSystemRepository.save(bytes, imageName);
        question.setQuestionLocation(location);
        questionRepository.save(question);
    }

    public FileSystemResource find(Long id) {
        Question question = findQuestionById(id);
        return fileSystemRepository.findInFileSystem(question.getQuestionLocation());
    }

    @Override
    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    @Transactional
    public boolean addQuestion(Question question) {
        Question isExist = null;
        try {
            isExist = findQuestionById(question.getId());
        } catch (EntityNotFoundException ex) {
            logger.warning(ex.getMessage());
        }
        if(isExist != null) {
            return false;
        }
        questionRepository.save(question);
        return true;
    }

    @Override
    public Question findQuestionById(Long id) {
        Optional<Question> optionalQuestion = questionRepository.getQuestionById(id);
        if(optionalQuestion.isPresent()){
            return optionalQuestion.get();
        }
        throw new EntityNotFoundException("This question does not exist");
    }
}
