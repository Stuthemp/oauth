package stuthemp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import stuthemp.model.Question;
import stuthemp.model.QuestionType;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for QuestionManagementController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-questions-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/delete-questions-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class QuestionManagementControllerTest extends TestCase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuestionManagementController questionManagementController;

    @Test
    public void getAllQuestionsTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Question question1 = new Question();
        question1.setQuestionLocation("C://");
        question1.setWrongAnswer1("B");
        question1.setWrongAnswer2("C");
        question1.setWrongAnswer3("D");
        question1.setRightAnswer("A");
        question1.setDocRef("http");
        question1.setQuestionType(QuestionType.get("embed_types"));
        question1.setId(1L);

        Question question2 = new Question();
        question2.setQuestionLocation("D://");
        question2.setWrongAnswer1("2");
        question2.setWrongAnswer2("3");
        question2.setWrongAnswer3("4");
        question2.setRightAnswer("1");
        question2.setDocRef("https");
        question2.setQuestionType(QuestionType.get("exceptions"));
        question2.setId(2L);

        List<Question> expected = new ArrayList<>(List.of(question1, question2));
        expected.sort((o1, o2) -> Long.valueOf(o1.getId() - o2.getId()).intValue());

        MvcResult response = mockMvc.perform(get("/questions/all"))
            .andDo(print())
            .andReturn();
        String content = response.getResponse().getContentAsString();

        List<Question> result = objectMapper.readValue(content, new TypeReference<>(){});
        result.sort((o1, o2) -> Long.valueOf(o1.getId() - o2.getId()).intValue());

        for (int i = 0; i < expected.size(); i++) {
            assertTrue(expected.get(i).semanticEquality(result.get(i)));
        }
    }

    @Test
    public void addQuestionTest() throws Exception {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("rightAnswer","right");
        parameters.add("wrongAnswer1", "w1");
        parameters.add("wrongAnswer2","w2");
        parameters.add("wrongAnswer3", "w3");
        parameters.add("questionType", "qt");
        parameters.add("name","name");
        parameters.add("docRef", "dr");

        MockHttpServletRequestBuilder multipart = multipart("/add/question")
            .file("photo","213".getBytes())
            .headers(new HttpHeaders(parameters));

        mockMvc.perform(multipart)
            .andExpect(status().isOk());
    }

    @Test
    public void getQuestionByTypeTest() throws Exception {
        String uri = "/questions/exceptions";
        String expectedType = "exceptions";

        MvcResult result = mockMvc
            .perform(get(uri))
            .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Question> questions = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){});

        for (Question question: questions) {
            Assert.assertEquals(expectedType,question.getQuestionType().getName());
        }
    }

    @Test
    public void findQuestionByIdTest() throws Exception{
        MvcResult result = mockMvc
            .perform(get("/question/1"))
            .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        Question question = objectMapper.readValue(result.getResponse().getContentAsString(), Question.class);

        Assert.assertSame(1L,question.getId());
    }



}