package cnu.project.blog.postlike.service;

import cnu.project.blog.post.domain.Post;
import cnu.project.blog.post.repository.PostRepository;
import cnu.project.blog.postlike.domain.PostLike;
import cnu.project.blog.postlike.dto.PostLikeResponseDto;
import cnu.project.blog.postlike.repository.PostLikeRepository;
import cnu.project.blog.user.User;
import cnu.project.blog.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final UserService userService;

    // 좋아요 추가
    public void addLike(Long postId, String userEmail) {
        User user = userService.findUserByEmail(userEmail);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        if (!postLikeRepository.existsByUserAndPost(user, post)) {
            PostLike like = PostLike.builder()
                    .user(user)
                    .post(post)
                    .build();
            postLikeRepository.save(like);
        }
    }

    // 좋아요 제거
    public void removeLike(Long postId, String userEmail) {
        User user = userService.findUserByEmail(userEmail);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        postLikeRepository.deleteByUserAndPost(user, post);
    }

    // 특정 게시글의 좋아요 개수 조회
    public long getPostLikeCount(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));
        return postLikeRepository.countByPost(post);
    }

    // 특정 게시글 좋아요 누른 모든 유저 조회
    public List<PostLikeResponseDto> getLikesByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        return postLikeRepository.findAllByPost(post)
                .stream()
                .map(like -> PostLikeResponseDto.builder()
                        .likeId(like.getId())
                        .userId(like.getUser().getId())
                        .postId(postId)
                        .build())
                .toList();
    }

    // 특정 유저가 누른 모든 좋아요 조회
    public List<PostLikeResponseDto> getLikesByUser(String userEmail) {
        User user = userService.findUserByEmail(userEmail);

        return postLikeRepository.findAllByUser(user)
                .stream()
                .map(like -> PostLikeResponseDto.builder()
                        .likeId(like.getId())
                        .userId(user.getId())
                        .postId(like.getPost().getId())
                        .build())
                .toList();
    }
}
