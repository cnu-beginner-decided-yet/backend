package cnu.project.blog.user;

import cnu.project.blog.user.User;
import lombok.Builder;
import lombok.Getter;

/**
 * 타인 프로필 조회 응답 DTO
 * - nickname, organization, bio 등 공개 가능한 정보만 포함
 */
@Getter
@Builder
public class UserProfilePublicDto {
    // ID, email, createdAt 등 민감 정보는 제외
    private Long id;
    private String nickname;
    private String organization;
    private String bio;

    // User 엔티티를 DTO로 변환하는 정적 팩토리 메서드
    public static UserProfilePublicDto from(User user) {
        return UserProfilePublicDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .organization(user.getOrganization())
                .bio(user.getBio())
                .build();
    }
}