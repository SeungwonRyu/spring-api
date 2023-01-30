package com.example.hanebaapi.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/id")
public class UserController {
    private final UserService userServ;

    @GetMapping("/register")
    public String getRegisterPage() {
        return "/id/register";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "/id/login";
    }

    @PostMapping("/save")
    public String save(User.RequestDTO reqDTO) {
        String url = "/error/blank";
        if(userServ.save(reqDTO) > 0) url = "redirect:/id/login";

        return url;
    }

    @PostMapping("/count-email")
    public String countByEmailAndDropYn(User.RequestDTO reqDTO) {
        return "jsonView";
    }
}
