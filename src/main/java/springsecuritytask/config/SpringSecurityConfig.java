package springsecuritytask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig { // SpringSecurity 인가, 검증 을 커스텀하는 클래스

    // [1] 비밀번호 암호화 메소드
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    // [2] SpringSecurity 인가, 검증 커스텀
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        
        // 각 경로에 대한 설정
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc").permitAll() // 모든 사용자 접근 허용
                .requestMatchers("/admin").hasRole("ADMIN") // "ADMIN" 롤을 가진 사용자만 접근 가능
                .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER") 
                .anyRequest().authenticated() // 위에 지정한 경로에 해당되지 않는 경로에 대한 처리
        );
        
        // 로그인 페이지 설정
        http.formLogin((auth) -> auth.loginPage("/login")
                .loginProcessingUrl("/loginProc")// 로그인 HTML 파일을 통해 사용자에게 입력 받은 정보를 지정한 경로로 보냄
                .defaultSuccessUrl("/mypage", true) // 로그인 성공 시 이동할 경로
                .permitAll()
        );

        // 로그아웃 페이지 설정
        http.logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/join") // 로그아웃 성공 후 이동할 경로
                .permitAll() // 모든 사용자 접근 가능
        );
        
        // 토큰 사용 X
        http.csrf((auth) -> auth.disable());
        
        return http.build();
    }
    
}
