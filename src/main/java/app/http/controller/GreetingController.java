package app.http.controller;

import app.dto.LoginDto;
import app.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @GetMapping("/hello")
    public String hello(Model model, @ModelAttribute("loginDto") LoginDto loginDto) {
        model.addAttribute("user", loginDto);
        return "hello";
    }

    @GetMapping("/bye")
    public String bye(@ModelAttribute("user") LoginDto user) {

        return "bye";
    }
}
