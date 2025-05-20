package springsecuritytask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import springsecuritytask.service.LoginService;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private  final LoginService loginService;
}
