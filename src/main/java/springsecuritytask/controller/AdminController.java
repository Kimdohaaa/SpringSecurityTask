package springsecuritytask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController { // admin 만 접근 가능한 페이지(로그인하지 않은 상태고 "ADMIN" 롤이 없다면 액세스 거부)

    @GetMapping("/admin")
    public String adminP(){
        return "admin";
    }
}
