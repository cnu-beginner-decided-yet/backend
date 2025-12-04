package cnu.project.blog.comment.service;

import cnu.project.blog.comment.dto.CommentRequestDto;
import cnu.project.blog.comment.dto.CommentResponseDto;
import cnu.project.blog.comment.entity.Comment;
import cnu.project.blog.comment.repository.CommentRepository;
import cnu.project.blog.post.domain.Post;
import cnu.project.blog.post.repository.PostRepository;
import cnu.project.blog.user.User;
import cnu.project.blog.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public CommentResponseDto createComment(CommentRequestDto commentRequestDto){

        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(()->new RuntimeException("Post not found."));
        User user = userRepository.findById(commentRequestDto.getUserId()).orElseThrow(()->new RuntimeException("User not found."));

        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .content(commentRequestDto.getContent())
                .build();

        Comment saveComment = commentRepository.save(comment);

        return CommentResponseDto.from(saveComment);
    }


    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto){

        Comment comment = commentRepository.findById(id).orElseThrow(()->new RuntimeException("Comment not found."));

        comment.update(commentRequestDto);

        return CommentResponseDto.from(comment);
    }


    public void deleteComment(Long id){

        Comment comment = commentRepository.findById(id).orElseThrow(()->new RuntimeException("Comment not found."));
        commentRepository.deleteById(id);

    }


    public List<CommentResponseDto> findAllComment(){

        List<Comment> commentList = commentRepository.findAll();

        return CommentResponseDto.fromList(commentList);

    }


    public List<CommentResponseDto> findCommentByUserId(Long userId){

        List<Comment> commentList = commentRepository.findByUserId(userId);

        return commentList.stream()
                .map(comment -> CommentResponseDto.from(comment)
                )
                .toList();
    }


    public List<CommentResponseDto> findCommentByPostId(Long postId){

        List<Comment> commentList = commentRepository.findByPostId(postId);

        return commentList.stream()
                .map(comment -> CommentResponseDto.from(comment)
                )
                .toList();

    }

}
