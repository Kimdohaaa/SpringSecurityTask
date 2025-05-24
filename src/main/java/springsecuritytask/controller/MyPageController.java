package springsecuritytask.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springsecuritytask.model.dto.CustomUserDetails;

@Controller
public class MyPageController {
    @GetMapping("/mypage")
    public String mypage(
            @AuthenticationPrincipal CustomUserDetails userDetails, // 로그인된 사용자의 정보 주입 받기
            Model model // View 부분으로 데이터를 전달하기 위한 Model 객체
    ) {

        // Model 객체에 "useremail" 로 사용자의 이메일 저장
        model.addAttribute("useremail", userDetails.getUseremail());
        // Model 객체에 "username"으로 사용자의 이름 저장
        model.addAttribute("username", userDetails.getUsernameReal());
        // Model 객체에 "role"로 사용자 Role 저장
        model.addAttribute("role", userDetails.getRole());
        // Model 객체에 "isAdmin"으로 사용자의 Role 이 "ADMIN" 이면 true 아니면 false 저장
        model.addAttribute("isAdmin", userDetails.getRole().equals("ROLE_ADMIN"));

        return "mypage";
    }

}
