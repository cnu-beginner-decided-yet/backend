package cnu.project.blog.post.controller;

import cnu.project.blog.post.dto.PostRequestDto;
import cnu.project.blog.post.dto.PostResponseDto;
import cnu.project.blog.post.service.PostService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
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

    @PostMapping("/{id}/like")
    public String likePost(@PathVariable Long id) {
        postService.likePost(id);
        return "좋아요 처리 완료";
    }

    @PostMapping("/{id}/unlike")
    public String unlikePost(@PathVariable Long id) {
        postService.unlikePost(id);
        return "좋아요 취소 완료";
    }

    @GetMapping("/search/title")
    public List<PostResponseDto> searchByTitle(@RequestParam String keyword) {
        return postService.searchByTitle(keyword);
    }

    @GetMapping("/search/content")
    public List<PostResponseDto> searchByContent(@RequestParam String keyword) {
        return postService.searchByContent(keyword);
    }

    @GetMapping("/search/tag")
    public List<PostResponseDto> searchByTag(@RequestParam String tag) {
        return postService.searchByTag(tag);
    }
}
