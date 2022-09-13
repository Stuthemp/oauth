package stuthemp.serive;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import stuthemp.model.Question;
import stuthemp.repository.QuestionRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * todo Document type QuestionServiceImplTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceImplTest {

    @MockBean
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionServiceImpl questionService;

    @Test
    public void addQuestionTest() {
        Question question = new Question();
        question.setId(999L);

        boolean isCreated = questionService.addQuestion(question);

        Assert.assertTrue(isCreated);

        Mockito.verify(questionRepository, Mockito.times(1)).save(question);
    }

    @Test
    public void addQuestionFailTest() {
        Question question = new Question();
        question.setId(1L);

        Mockito.doReturn(Optional.of(new Question()))
            .when(questionRepository)
            .getQuestionById(1L);

        boolean isCreated = questionService.addQuestion(question);

        Assert.assertFalse(isCreated);
        Mockito.verify(questionRepository, Mockito.times(0)).save(ArgumentMatchers.any(Question.class));
    }

    @Test
    public void getQuestionByIdTest() {
        long id = 1L;

        Mockito.doReturn(Optional.of(new Question()))
            .when(questionRepository)
            .getQuestionById(id);

        Question question = questionService.findQuestionById(id);

        Assert.assertNotNull(question);
    }

    @Test(expected = EntityNotFoundException.class)
    public void getTestByIdFail() {
        long id = 1L;

        Mockito.doReturn(Optional.empty())
            .when(questionRepository)
            .getQuestionById(id);

        Question question = questionService.findQuestionById(id);
    }

}