package cnu.project.blog.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequestDto {
    
    private String email;
    private String password;
    private String nickname;
}
