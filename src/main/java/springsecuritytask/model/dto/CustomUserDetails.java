package springsecuritytask.model.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import springsecuritytask.model.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    // 데이터를 받을 필드 선언
    private UserEntity userEntity;

    // 데이터를 주입받기 위한 생성자 선언
    public CustomUserDetails(UserEntity userEntity) {

        this.userEntity = userEntity;
    }


    // [1] 사용자의 권한 리턴 메소드(롤)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // 1) 컬렉션 객체 생성
        Collection<GrantedAuthority> collection = new ArrayList<>();

        // 2) 롤 데이터 넣어주기
        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return userEntity.getRole(); // 전달받은 롤 값 대입
            }
        });

        // 컬렉션 객체 리턴
        return collection;
    }

    // [2] 비밀번호
    @Override
    public String getPassword() {
        return userEntity.getUserpassword();
    }

    // [3] 이메일
    @Override
    public String getUsername() {
        return userEntity.getUseremail();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
