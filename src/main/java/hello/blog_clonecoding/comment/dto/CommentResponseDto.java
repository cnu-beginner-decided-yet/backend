package hello.blog_clonecoding.comment.dto;


import hello.blog_clonecoding.comment.entity.Comment;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentResponseDto {

    Long id;
    String content;
    Long postId;
    Long userId;

    public static CommentResponseDto from(Comment comment){
        return CommentResponseDto.builder()
                .id(comment.getId())
                .userId(comment.getUser().getId())
                .postId(comment.getPost().getId())
                .content(comment.getContent())
                .build();
    }

    public static List<CommentResponseDto> fromList(List<Comment> comments) {
        return comments.stream()
                .map(CommentResponseDto::from)
                .toList();
    }

}
