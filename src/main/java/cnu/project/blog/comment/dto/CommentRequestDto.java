package cnu.project.blog.comment.dto;


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
