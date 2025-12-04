package cnu.project.blog.postlike.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLikeResponseDto {

    private Long likeId;
    private Long userId;
    private Long postId;
}
