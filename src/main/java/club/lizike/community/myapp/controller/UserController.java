package club.lizike.community.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("signUp")
    public String get(){
        return "signUp";
    }

}
