package hello.blog_clonecoding.comment.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequestDto {

    Long postId;
    String content;
    Long userId;

}
