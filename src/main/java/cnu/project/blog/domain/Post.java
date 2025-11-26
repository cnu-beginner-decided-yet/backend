package cnu.project.blog.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 게시글 엔티티
 * - tags는 문자열 리스트로 저장
 * - 카테고리와 다대일 관계
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ElementCollection
    @Builder.Default
    private List<String> tags = new ArrayList<>();

    public void setTags(List<String> tags) {
        this.tags = tags.stream().map(t -> t.trim().toUpperCase()).filter(t -> !t.isBlank()).distinct().toList();
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder.Default
    private int likes = 0;  // 좋아요 수
}
