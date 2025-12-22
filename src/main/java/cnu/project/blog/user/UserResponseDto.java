package cnu.project.blog.user;
import cnu.project.blog.user.User;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String email;
    private String nickname;
    private String organization;
    private String bio;
    private LocalDateTime createdAt;

    // User 엔티티를 DTO로 변환하는 정적 팩토리 메서드
    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .organization(user.getOrganization())
                .bio(user.getBio())
                .createdAt(user.getCreatedAt())
                .build();
    }
}