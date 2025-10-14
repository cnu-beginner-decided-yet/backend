package cnu.project.blog.service;

import cnu.project.blog.domain.Category;
import cnu.project.blog.domain.Post;
import cnu.project.blog.dto.CategoryDto;
import cnu.project.blog.dto.PostResponseDto;
import cnu.project.blog.repository.CategoryRepository;
import cnu.project.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 카테고리 관련 비즈니스 로직
 * - CRUD
 * - 카테고리별 게시글 조회
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;

    // 카테고리 생성
    public CategoryDto createCategory(CategoryDto dto) {
        Category category = Category.builder()
                .name(dto.getName())
                .build();
        Category saved = categoryRepository.save(category);
        return toDto(saved);
    }

    // 모든 카테고리 조회
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ID로 카테고리 조회
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("카테고리 없음"));
        return toDto(category);
    }

    // 카테고리 수정
    public CategoryDto updateCategory(Long id, CategoryDto dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("카테고리 없음"));
        category.setName(dto.getName());
        return toDto(category);
    }

    // 카테고리 삭제
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    // 카테고리별 게시글 조회
    public List<PostResponseDto> getPostsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("카테고리 없음"));
        List<Post> posts = postRepository.findAll().stream()
                .filter(post -> post.getCategory() != null && post.getCategory().getId().equals(categoryId))
                .collect(Collectors.toList());
        return posts.stream()
                .map(post -> PostResponseDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .tags(post.getTags())
                        .categoryName(post.getCategory().getName())
                        .likes(post.getLikes())
                        .build())
                .collect(Collectors.toList());
    }

    // Category -> DTO 변환
    private CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
