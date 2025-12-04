package hello.blog_clonecoding.comment.controller;


import hello.blog_clonecoding.comment.dto.CommentRequestDto;
import hello.blog_clonecoding.comment.dto.CommentResponseDto;
import hello.blog_clonecoding.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public CommentResponseDto createComment(@RequestBody CommentRequestDto commentRequestDto){
        return commentService.createComment(commentRequestDto);
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


}
