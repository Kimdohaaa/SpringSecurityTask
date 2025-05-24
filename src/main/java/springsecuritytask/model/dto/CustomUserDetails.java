package springsecuritytask.model.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import springsecuritytask.model.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;

/*
- CustomUserDetails 클래스는 UserDetails 인터페이스를 구현함
- DB 에서 조회한 사용자 정보를 SpringSecurity 를 통해 인증, 권한 체크 하기 위해 사용
- username/pasword/authorities/계정상태 오버라이딩 필수
- 로그인 성공 후 CustomUserDetails 객체를 통해 SpringSecurity 에서 로그인된 사용자의 저보를 가져올 수 있음
*/
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

    // 추가로 사용할 데이터

    // [1] Entity
    public UserEntity getUserEntity() {
        return this.userEntity;
    }

    // [2] 이메일
    public String getUseremail() {
        return userEntity.getUseremail();
    }

    // [3] 이름
    public String getUsernameReal() {
        return userEntity.getUsername();
    }

    // [4] Role
    public String getRole() {
        return userEntity.getRole();
    }

}
