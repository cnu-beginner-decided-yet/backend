package cnu.project.blog.post.dto;

import lombok.*;

import java.util.List;

/**
 * 게시글 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private List<String> tags;
    private String categoryName;
    private long likes;
}
