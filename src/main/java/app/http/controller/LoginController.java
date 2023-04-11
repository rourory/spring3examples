package app.http.controller;

import app.dto.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute("loginDto") LoginDto loginDto){
        if (loginDto.getPassword().equals("123"))
            return "redirect:/api/v1/hello";
        return  "login";
    }
}
