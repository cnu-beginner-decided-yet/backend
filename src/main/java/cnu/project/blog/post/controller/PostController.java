package cnu.project.blog.post.controller;

import cnu.project.blog.post.domain.Post;
import cnu.project.blog.post.dto.PostRequestDto;
import cnu.project.blog.post.dto.PostResponseDto;
import cnu.project.blog.post.service.PostService;
import cnu.project.blog.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시글 관련 REST API
 * - CRUD, 좋아요, 검색
 */
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, Authentication authentication) {
        return postService.createPost(requestDto, authentication.getName());
    }

    @GetMapping
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostResponseDto getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "게시글 삭제 완료";
    }

    @GetMapping("/search/title")
    public List<PostResponseDto> searchByTitle(@RequestParam String keyword) {
        return postService.searchByTitle(keyword);
    }

    @GetMapping("/search/content")
    public List<PostResponseDto> searchByContent(@RequestParam String keyword) {
        return postService.searchByContent(keyword);
    }

    @GetMapping("/search/{author_id}")
    public List<PostResponseDto> searchByAuthor(@PathVariable Long author_id) {
        return postService.findPostsByAuthorId(author_id);
    }

    @GetMapping("/search/tag")
    public List<PostResponseDto> searchByTag(@RequestParam String tag) {
        return postService.searchByTag(tag);
    }

    /**
     * 현재 로그인한 사용자가 작성한 모든 게시글 목록을 조회합니다.
     * 이 API는 JWT 토큰이 필요합니다.
     * GET /api/posts/my
     */
    @GetMapping("/my")
    public ResponseEntity<List<PostResponseDto>> getMyPosts(Authentication authentication) {

        // 1. Spring Security의 Authentication 객체에서 인증된 사용자 정보(Principal)를 추출
        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 2. JWT Payload의 Subject(사용자 이메일)를 가져옵니다.
        String principalEmail = authentication.getName();

        try {
            // 3. UserService를 사용하여 이메일로 실제 User ID를 찾습니다.
            Long userId = userService.findUserIdByEmail(principalEmail);

            // 💡 4. 서비스 호출 결과 변수 타입을 List<PostResponseDto>로 변경
            List<PostResponseDto> myPosts = postService.findPostsByAuthorId(userId);

            // 5. myPosts가 List<PostResponseDto> 타입이 되었지만, ResponseEntity.ok()는 그대로 사용합니다.
            return ResponseEntity.ok(myPosts);
        } catch (IllegalArgumentException e) {
            // 해당 이메일의 사용자가 DB에 없는 경우 (404 처리)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
