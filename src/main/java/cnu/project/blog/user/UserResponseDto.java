package cnu.project.blog.user;
import cnu.project.blog.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 사용자 정보 조회 응답 DTO
 * - 민감한 정보(password 등)는 제외하고 필요한 정보만 노출
 */
@Getter
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