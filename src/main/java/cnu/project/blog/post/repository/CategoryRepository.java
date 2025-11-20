package cnu.project.blog.post.repository;

import cnu.project.blog.post.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 카테고리 Repository
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
