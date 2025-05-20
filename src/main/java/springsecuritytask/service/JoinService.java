package springsecuritytask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springsecuritytask.model.dto.UserDTO;
import springsecuritytask.model.entity.UserEntity;
import springsecuritytask.model.repository.UserRepository;

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

        // 중복 이메일 확인
        userRepository.existsByUseremail(userDTO.getUseremail());

        // 비밀번호 암호화
        userDTO.setUserpassword(bCryptPasswordEncoder.encode(userDTO.getUserpassword()));

        // 권한부여
        userDTO.setRole("ROLE_ADMIN"); // 임시로 관리자 권한 부여

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
