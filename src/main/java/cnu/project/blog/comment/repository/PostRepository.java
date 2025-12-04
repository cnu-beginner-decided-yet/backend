package cnu.project.blog.comment.repository;

import cnu.project.blog.comment.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    Optional<Post> findByUserId(Long userId);

    Optional<Post> findByCategoryId(Long categoryId);

    Optional<Post> findByTitleContaining(String keyword);

    Optional<Post> findByTextContaining(String keyword);

    //~~

}
