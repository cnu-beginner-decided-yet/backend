package cnu.project.blog.post.dto;

import lombok.*;

/**
 * 카테고리 생성/수정 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private Long id;       // 카테고리 ID
    private String name;   // 카테고리 이름
}
