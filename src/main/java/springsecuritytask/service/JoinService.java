package springsecuritytask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springsecuritytask.model.dto.UserDTO;
import springsecuritytask.model.entity.UserEntity;
import springsecuritytask.model.repository.UserRepository;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class JoinService {
    private  final UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    // 회원가입
    public boolean joinProcess(UserDTO userDTO){
        System.out.println("JoinService.joinProcess");
        System.out.println("userDTO = " + userDTO);
        // 유효성 검사 //
        // [1] 중복 이메일 확인
        if(userRepository.existsByUseremail(userDTO.getUseremail())){
            System.out.println(">>> 이메일 중복");
            return false;
        };
        // [2] 이메일 길이
        if(userDTO.getUseremail().length() < 5 || userDTO.getUseremail().length() > 17){
            System.out.println(">>> 이메일 길이 오류 ");
            return false ;
        }
        // [2] 이메일 형식 검사
        String emailCheck = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if(!Pattern.matches(emailCheck, userDTO.getUseremail())){
            System.out.println(">>> 이메일 형식 오류 ");
            return  false;
        }
        // [3] 비밀번호 복잡도 검사
        String passwordCheck = "^(?=.*\\d)[A-Za-z\\d]{5,20}$";
        if(!Pattern.matches(passwordCheck, userDTO.getUserpassword())){
            System.out.println(">>> 비밀번호 복잡도 오류 ");
            return false;
        }

        // 비밀번호 암호화
        userDTO.setUserpassword(bCryptPasswordEncoder.encode(userDTO.getUserpassword()));

        // 권한부여
        userDTO.setRole("ROLE_USER"); // 기본 권한으로 USER 부여

        // DTO -> Entity
        UserEntity userEntity = userDTO.toEntity();

        UserEntity saveEntity = userRepository.save(userEntity);

        if(saveEntity.getUno() > 0){
            return true;
        }else {
            return false;
        }

    }

}
