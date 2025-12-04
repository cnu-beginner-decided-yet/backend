package cnu.project.blog.post.repository;

import cnu.project.blog.post.domain.Post;
import cnu.project.blog.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 게시글 Repository
 * - CRUD, 검색 메서드 정의
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContaining(String keyword);
    List<Post> findByContentContaining(String keyword);
    List<Post> findByTagsContaining(String tag);
    List<Post> findAllByAuthor(User author);
}
