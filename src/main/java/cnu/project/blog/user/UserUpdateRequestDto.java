package cnu.project.blog.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 사용자 정보 수정 요청 DTO (개인 정보 추가/수정용)
 */
@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String nickname;
    private String organization;
    private String bio;
}