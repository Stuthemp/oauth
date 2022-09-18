package proj.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj.model.QuestionLog;
import proj.persistance.FirstRepository;

import java.util.List;

/**
 * todo Document type FirstService
 */
@Service
public class FirstService {

    FirstRepository repository;

    @Autowired
    public FirstService(FirstRepository repository) {
        this.repository = repository;
    }

    public List<QuestionLog> findAll() {
        return repository.findAll();
    }

    public QuestionLog addQuestion(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        QuestionLog questionLog = null;
        try {
            questionLog = objectMapper.readValue(json,QuestionLog.class);
        } catch (JsonProcessingException e) {
            System.out.println(e);
        }

        repository.save(questionLog);
        return questionLog;
    }
}
