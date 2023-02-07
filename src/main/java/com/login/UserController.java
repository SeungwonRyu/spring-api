package com.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
* 가입 및 로그인 페이지 동작 메소드
*/
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
    public String getLoginPage(
            Model model,
            @RequestParam(value = "error", required = false) String err,
            @RequestParam(value = "exception", required = false) String e
    ) {
        model.addAttribute("error", err);
        model.addAttribute("exception", e);

        return "/id/login";
    }

    @PostMapping("/save")
    public String save(User.RequestDTO reqDTO) {
        String url = "/error/blank";
        if(userServ.save(reqDTO) > 0) url = "redirect:/id/login";

        return url;
    }

    @PostMapping("/count-email")
    public String countByEmailAndDropYn(Model model, User.RequestDTO reqDTO) {
        model.addAttribute(
                "count",
                userServ.countByEmailAndDropYn(reqDTO.getEmail(), reqDTO.getDropYn()));
        return "jsonView";
    }
}
