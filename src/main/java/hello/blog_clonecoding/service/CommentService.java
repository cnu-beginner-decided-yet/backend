package hello.blog_clonecoding.service;

import hello.blog_clonecoding.repository.CommentRepository;
import hello.blog_clonecoding.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


}
