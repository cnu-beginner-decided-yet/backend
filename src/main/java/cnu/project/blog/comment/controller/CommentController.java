package cnu.project.blog.comment.controller;


import cnu.project.blog.comment.dto.CommentRequestDto;
import cnu.project.blog.comment.dto.CommentResponseDto;
import cnu.project.blog.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;


    @GetMapping
    public List<CommentResponseDto> findAllComment(){
        return commentService.findAllComment();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto createComment(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CommentRequestDto commentRequestDto){
        return commentService.createComment(userDetails.getUsername(), commentRequestDto);
    }


    @PatchMapping("/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto){
        return commentService.updateComment(id, commentRequestDto);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }


    @GetMapping("/users/{userId}")
    public List<CommentResponseDto> findCommentByUserEmail(@AuthenticationPrincipal UserDetails userDetails){
        return commentService.findCommentByUserEmail(userDetails.getUsername());
    }


    @GetMapping("/posts/{postId}")
    public List<CommentResponseDto> findCommentByPostId(@PathVariable Long postId){
        return commentService.findCommentByPostId(postId);
    }

}
