package net.camillasatte.mynewhome.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "index";
    }
    
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
    	System.out.println("EMAIL:;login");
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
