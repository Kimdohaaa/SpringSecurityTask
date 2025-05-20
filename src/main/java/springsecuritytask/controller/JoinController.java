package springsecuritytask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import springsecuritytask.service.JoinService;

@Controller
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;

}
