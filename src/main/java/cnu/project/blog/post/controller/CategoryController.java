package cnu.project.blog.post.controller;

import cnu.project.blog.post.dto.CategoryDto;
import cnu.project.blog.post.dto.PostResponseDto;
import cnu.project.blog.post.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 카테고리 관련 REST API
 * - CRUD
 * - 카테고리별 게시글 조회
 */
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 생성
    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryDto dto) {
        return categoryService.createCategory(dto);
    }

    // 모든 카테고리 조회
    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // ID로 카테고리 조회
    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    // 카테고리 수정
    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryDto dto) {
        return categoryService.updateCategory(id, dto);
    }

    // 카테고리 삭제
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "카테고리 삭제 완료";
    }

    // 카테고리별 게시글 조회
    @GetMapping("/{id}/posts")
    public List<PostResponseDto> getPostsByCategory(@PathVariable Long id) {
        return categoryService.getPostsByCategory(id);
    }
}
