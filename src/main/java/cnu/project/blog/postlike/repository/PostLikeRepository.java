package cnu.project.blog.postlike.repository;

import cnu.project.blog.post.domain.Post;
import cnu.project.blog.postlike.domain.PostLike;
import cnu.project.blog.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    boolean existsByUserAndPost(User user, Post post);
    long countByPost(Post post);
    List<PostLike> findAllByPost(Post post);
    List<PostLike> findAllByUser(User user);
    void deleteByUserAndPost(User user, Post post);
}
