package cnu.project.blog.postlike.controller;

import cnu.project.blog.postlike.dto.PostLikeResponseDto;
import cnu.project.blog.postlike.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class PostLikeController {

    private final PostLikeService postLikeService;

    // 좋아요 추가
    @PostMapping("/{postId}")
    public String likePost(@PathVariable Long postId, Authentication auth) {
        postLikeService.addLike(postId, auth.getName());
        return "좋아요 추가 완료";
    }

    // 좋아요 취소
    @DeleteMapping("/{postId}")
    public String unlikePost(@PathVariable Long postId, Authentication auth) {
        postLikeService.removeLike(postId, auth.getName());
        return "좋아요 취소 완료";
    }

    // 특정 게시글의 좋아요 개수
    @GetMapping("/post/{postId}/count")
    public long getPostLikeCount(@PathVariable Long postId) {
        return postLikeService.getPostLikeCount(postId);
    }

    // 특정 게시글의 좋아요 전체 조회
    @GetMapping("/post/{postId}")
    public List<PostLikeResponseDto> getLikesByPost(@PathVariable Long postId) {
        return postLikeService.getLikesByPost(postId);
    }

    // 특정 유저가 누른 좋아요 전체 조회
    @GetMapping("/user")
    public List<PostLikeResponseDto> getLikesByUser(Authentication auth) {
        return postLikeService.getLikesByUser(auth.getName());
    }
}
