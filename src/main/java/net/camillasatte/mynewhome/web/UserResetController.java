package net.camillasatte.mynewhome.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.camillasatte.mynewhome.model.User;
import net.camillasatte.mynewhome.service.UserService;

@Controller
@RequestMapping("/forgotPassword")
public class UserResetController {

    @Autowired
    private UserService userService;
//
//    @ModelAttribute("user")
//    public UserRegistrationDTO userRegistrationDto() {
//        return new UserRegistrationDTO();
//    }

    @GetMapping
    public String showForgotPasswordForm(Model model) {
    	System.out.println("showForgotPasswordForm:");

        return "forgotPassword";
    }

    @PostMapping
    public String resetUserAccount(@RequestParam(value = "email", required = false) String email, 
    		@RequestParam(value = "password", required = false) String password,
    		@RequestParam(value = "confirmPassword", required = false) String confirmPassword) {
    	System.out.println("EMAIL:"+email);
    	System.out.println("password:"+password);
    	System.out.println("confirmPassword:"+confirmPassword);
        User existing = userService.findByEmail(email);
        
        if (existing != null) {
        	return "login";
        } else {
//        	result.rejectValue("email", null, "There no account registered with that email");
        
        	return "redirect:/registration";
        }
    }
  
}
