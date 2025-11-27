package cnu.project.blog.post.domain;

import cnu.project.blog.user.User;
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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // 💡 작성자(User)와의 관계 추가
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 설정
    @JoinColumn(name = "user_id") // DB 컬럼명을 user_id로 설정
    private User author;
}
