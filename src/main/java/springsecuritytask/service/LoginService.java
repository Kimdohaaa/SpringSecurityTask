package springsecuritytask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springsecuritytask.model.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class LoginService {
    private  final UserRepository userRepository;
}
