package cnu.project.blog.post.service;

import cnu.project.blog.post.domain.Category;
import cnu.project.blog.post.domain.Post;
import cnu.project.blog.post.dto.PostRequestDto;
import cnu.project.blog.post.dto.PostResponseDto;
import cnu.project.blog.post.repository.CategoryRepository;
import cnu.project.blog.post.repository.PostRepository;
import cnu.project.blog.postlike.repository.PostLikeRepository;
import cnu.project.blog.postlike.service.PostLikeService;
import cnu.project.blog.user.User;
import cnu.project.blog.user.UserRepository;
import cnu.project.blog.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시글 관련 비즈니스 로직
 * - CRUD, 좋아요, 검색
 */
@Service
@Transactional
@RequiredArgsConstructor  // final 필드 생성자 자동 생성
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final PostLikeRepository postLikeRepository;

    // 게시글 생성
    public PostResponseDto createPost(PostRequestDto requestDto, String author) {
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("카테고리 없음"));
        User authorUser = userService.findUserByEmail(author);

        Post post = Post.builder()
                .author(authorUser)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .tags(requestDto.getTags())
                .category(category)
                .build();

        return toDto(postRepository.save(post));
    }

    // 모든 게시글 조회
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ID로 게시글 조회
    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));
        return toDto(post);
    }

    public List<PostResponseDto> findPostsByAuthorId(Long userId) {

        // 1. userId를 사용하여 User 엔티티를 찾습니다.
        User author = userService.findUserById(userId);

        // 2. 해당 User 엔티티를 기준으로 게시글 목록을 조회합니다.
        return postRepository.findAllByAuthor(author).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // 게시글 수정
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());
        post.setTags(requestDto.getTags());

        if (requestDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(requestDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("카테고리 없음"));
            post.setCategory(category);
        }

        return toDto(post);
    }

    // 게시글 삭제
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // 제목 검색
    public List<PostResponseDto> searchByTitle(String keyword) {
        return postRepository.findByTitleContaining(keyword).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // 내용 검색
    public List<PostResponseDto> searchByContent(String keyword) {
        return postRepository.findByContentContaining(keyword).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // 태그 검색
    public List<PostResponseDto> searchByTag(String tag) {
        return postRepository.findByTagsContaining(tag).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Post -> DTO 변환
    private PostResponseDto toDto(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .tags(post.getTags())
                .categoryName(post.getCategory() != null ? post.getCategory().getName() : null)
                .likes(postLikeRepository.countByPost(post))
                .build();
    }
}
