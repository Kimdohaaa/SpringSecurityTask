package springsecuritytask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springsecuritytask.model.dto.UserDTO;
import springsecuritytask.service.CustomUserDetailsService;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private  final CustomUserDetailsService loginService;

    @GetMapping("/login")
    public String loginP(){
        return  "login"; // 로그인 페이지 반환
    }


}
