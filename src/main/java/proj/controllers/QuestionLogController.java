package proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.model.QuestionLog;
import proj.service.FirstService;

import java.util.List;

/**
 * todo Document type QuestionLogController
 */
@RestController
public class QuestionLogController {

    FirstService service;

    @Autowired
    public QuestionLogController(FirstService service) {
        this.service = service;
    }

    @GetMapping("/logs/all")
    public List<QuestionLog> findAllLogs() {
        return service.findAll();
    }
}
