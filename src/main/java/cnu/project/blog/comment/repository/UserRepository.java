package cnu.project.blog.comment.repository;

import cnu.project.blog.comment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User> findByNickname(String nickname);

    Optional<User> findByEmail(String email);

    //~~


}
