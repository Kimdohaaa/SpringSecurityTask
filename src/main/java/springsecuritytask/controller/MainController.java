package springsecuritytask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/") // resources/templates/main.mustache 가 실행됨
    // 기본값 (아이디 : user / 비밀번호 : 콘솔에 출력된 비밀번호)으로 로그인 시 main page 로 이동
    // -> 필터에서 요청을 가로채 로그인 정보를 확인함
    public  String mainP(){
        return "main";
    }
}
