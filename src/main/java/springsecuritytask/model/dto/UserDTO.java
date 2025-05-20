package springsecuritytask.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springsecuritytask.model.entity.UserEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private  int uno;
    private String username;
    private  String useremail;
    private  String userpassword;
    private String role;

    public UserEntity toEntity (){
        return UserEntity.builder()
                .uno(this.uno)
                .username(this.username)
                .useremail(this.useremail)
                .userpassword(this.userpassword)
                .role(this.role)
                .build();
    }

}
