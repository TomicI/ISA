package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {
	@RequestMapping("/")
    public String getHomepage() {
        return "index";
    }
	
	
}
