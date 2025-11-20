package cnu.project.blog.post.dto;

import lombok.*;

import java.util.List;

/**
 * 게시글 생성 / 수정 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDto {

    private String title;
    private String content;
    private List<String> tags;
    private Long categoryId;  // 선택한 카테고리 ID
}
