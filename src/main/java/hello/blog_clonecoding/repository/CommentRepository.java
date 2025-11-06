package hello.blog_clonecoding.repository;

import hello.blog_clonecoding.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

    Optional<Comment> findByPostId(Long postId);

    Optional<Comment> findByUserId(Long userId);


}
