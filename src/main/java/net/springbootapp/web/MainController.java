package net.springbootapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
    @GetMapping("/home")
    public String userHome() {
        return "home";
    }
    
    @GetMapping("/settings")
    public String userSettings() {
        return "settings";
    }
    
}
