package stuthemp.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo Document type HelloConroller
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        SecurityContext sc = SecurityContextHolder.getContext();
        String str = sc.getAuthentication().getPrincipal().toString();
        return str;
    }

}
