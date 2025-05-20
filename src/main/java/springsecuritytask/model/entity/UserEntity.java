package springsecuritytask.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springsecuritytask.model.dto.UserDTO;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int uno;

    private String username;
    @Column(unique = true)
    private  String useremail;
    private  String userpassword;
    private String role;

    public UserDTO toDTO(){
        return UserDTO.builder()
                .username(this.username)
                .useremail(this.useremail)
                .userpassword(this.userpassword)
                .role(this.role)
                .build();
    }

}
