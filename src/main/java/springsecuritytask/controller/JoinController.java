package springsecuritytask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import springsecuritytask.model.dto.UserDTO;
import springsecuritytask.service.JoinService;

@Controller
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;


    @GetMapping("/join")
    public String joinP(){
        return "join"; // 회원가입 페이지 반환
    }

    // 회원가입
    @PostMapping("/joinProc")
    public String joinProcess(UserDTO userDTO){
            //  JSON이 아니라 application/x-www-form-urlencoded 형식으로 받기 때문에 @RequestBody 사용 X
        System.out.println("JoinController.joinProcess");
        System.out.println("userDTO = " + userDTO);


        boolean result = joinService.joinProcess(userDTO);

        System.out.println(">>> 회원가입 결과 : " + result);

        return  "redirect:/login"; // 로그인페이지로 리다이렉팅
    }

}
