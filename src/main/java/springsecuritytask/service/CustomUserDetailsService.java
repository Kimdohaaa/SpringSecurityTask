package springsecuritytask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springsecuritytask.model.dto.CustomUserDetails;
import springsecuritytask.model.entity.UserEntity;
import springsecuritytask.model.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 로그인을 위해 useremail 검증
    @Override // UserDetailsSevice 의 메소드 오버라이딩
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByUseremail(useremail);

        if (userData == null) {
            throw new UsernameNotFoundException(">>> 사용자를 찾을 수 없음 " + useremail);
        }

        return new CustomUserDetails(userData);
    }
}