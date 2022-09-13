package stuthemp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * just test
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return "main.html";
    }

}
