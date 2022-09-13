package stuthemp.serive;

import org.springframework.core.io.FileSystemResource;
import stuthemp.model.Question;
import stuthemp.model.QuestionType;

import java.util.List;

public interface QuestionService {

    List<Question> findQuestionsByType(QuestionType type);

    boolean addQuestion(Question question);

    Question findQuestionById(Long id);
    void save(byte[] bytes, String imageName, Question question) throws Exception;

    FileSystemResource find(Long id);

    List<Question> findAllQuestions();
}
