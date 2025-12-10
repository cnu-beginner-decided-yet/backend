package cnu.project.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * 사용자 정보 (프로필) 관련 REST API
 * - 내 정보 조회, 프로필 수정, 타인 프로필 조회
 */
@RestController
@RequestMapping("/users/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;

    /**
     * 1. 내 정보 조회 (마이페이지)
     * 엔드포인트: GET /users/profile/my (JWT 토큰 필요)
     * 반환 DTO: UserResponseDto (모든 정보)
     */
    @GetMapping("/my")
    public ResponseEntity<UserResponseDto> getMyInfo(Authentication authentication) {
        if (authentication == null || authentication.getName() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String principalEmail = authentication.getName();

        try {
            UserResponseDto userInfo = userService.getUserInfoByEmail(principalEmail);
            return ResponseEntity.ok(userInfo);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 2. 개인 정보 수정 (마이페이지)
     * 엔드포인트: PUT /users/profile/my (JWT 토큰 필요)
     * 반환 DTO: UserResponseDto (수정된 모든 정보)
     */
    @PutMapping("/my")
    public ResponseEntity<UserResponseDto> updateMyInfo(
            @RequestBody UserUpdateRequestDto updateDto,
            Authentication authentication) {

        if (authentication == null || authentication.getName() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String principalEmail = authentication.getName();

        try {
            // Service 호출하여 정보 수정 및 DTO 반환
            UserResponseDto updatedUser = userService.updateUserInfo(principalEmail, updateDto);

            // 200 OK와 함께 수정된 정보를 반환
            return ResponseEntity.ok(updatedUser);

        } catch (IllegalArgumentException e) {
            // 사용자를 찾을 수 없는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    /**
     * 3. 다른 유저 프로필 조회 (닉네임 기반)
     * 엔드포인트: GET /users/profile/profile?nickname={nickname}
     * 반환 DTO: UserProfilePublicDto (공개 정보만) 💡
     */
    @GetMapping("/profile")
    public ResponseEntity<UserProfilePublicDto> getUserProfileByNickname(@RequestParam String nickname) {

        try {
            // Service 호출 시 반환 타입이 UserProfilePublicDto로 변경됨
            UserProfilePublicDto userInfo = userService.getUserInfoByNickname(nickname);

            // 200 OK와 함께 공개 정보를 반환
            return ResponseEntity.ok(userInfo);

        } catch (IllegalArgumentException e) {
            // 사용자를 찾을 수 없는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}