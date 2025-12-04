package hello.blog_clonecoding.comment.service;

import hello.blog_clonecoding.comment.entity.Comment;
import hello.blog_clonecoding.comment.entity.Post;
import hello.blog_clonecoding.comment.entity.User;
import hello.blog_clonecoding.comment.dto.CommentRequestDto;
import hello.blog_clonecoding.comment.dto.CommentResponseDto;
import hello.blog_clonecoding.comment.repository.CommentRepository;
import hello.blog_clonecoding.comment.repository.PostRepository;
import hello.blog_clonecoding.comment.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {


    @Mock
    CommentRepository commentRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    PostRepository postRepository;

    @InjectMocks
    CommentService commentService;   // Mock들을 주입받아 생성됨


    @Test
    void createComment() {
        // given
        CommentRequestDto req = new CommentRequestDto();
        req.setContent("내용");
        req.setPostId(1L);
        req.setUserId(1L);

        User user = User.builder().id(1L).build();
        Post post = Post.builder().id(1L).build();

        Comment saved = Comment.builder()
                .id(1L)
                .content("내용")
                .user(user)
                .post(post)
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.save(any(Comment.class))).thenReturn(saved);

        // when
        CommentResponseDto result = commentService.createComment(req);

        // then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getContent()).isEqualTo("내용");
    }


    @Test
    void createComment_postNotFound() {
        // given
        CommentRequestDto req = new CommentRequestDto();
        req.setPostId(1L);
        req.setUserId(1L);
        req.setContent("내용");

        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> commentService.createComment(req))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Post not found.");
    }

    @Test
    void createComment_userNotFound() {
        // given
        CommentRequestDto req = new CommentRequestDto();
        req.setPostId(1L);
        req.setUserId(1L);
        req.setContent("내용");

        when(postRepository.findById(1L)).thenReturn(Optional.of(new Post()));
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.createComment(req))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User not found.");
    }



    @Test
    void updateComment() {
        // given
        CommentRequestDto req = new CommentRequestDto();
        req.setContent("수정된 내용");

        User user = User.builder().id(1L).build();
        Post post = Post.builder().id(1L).build();

        Comment comment = Comment.builder()
                .id(1L)
                .content("원래 내용")
                .user(user)
                .post(post)
                .build();

        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        // when
        CommentResponseDto result = commentService.updateComment(1L, req);

        // then
        assertThat(result.getContent()).isEqualTo("수정된 내용");
        verify(commentRepository).findById(1L);
    }

    @Test
    void updateComment_notFound() {
        // given
        when(commentRepository.findById(1L)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> commentService.updateComment(1L, new CommentRequestDto()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Comment not found.");
    }



    @Test
    void deleteComment() {
        // given
        Comment comment = Comment.builder().id(1L).build();
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        // when
        commentService.deleteComment(1L);

        // then
        verify(commentRepository).deleteById(1L);  // 실제로 호출했는가?
    }

    @Test
    void deleteComment_notFound() {
        // given
        when(commentRepository.findById(1L)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> commentService.deleteComment(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Comment not found.");
    }



    @Test
    void findAllComment() {
        // given
        User user = User.builder().id(1L).build();
        Post post = Post.builder().id(1L).build();
        Comment c = Comment.builder()
                .id(1L)
                .content("내용")
                .user(user)
                .post(post)
                .build();
        when(commentRepository.findAll()).thenReturn(List.of(c));

        // when
        List<CommentResponseDto> result = commentService.findAllComment();

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getContent()).isEqualTo("내용");
    }




}
