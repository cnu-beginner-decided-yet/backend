package hello.blog_clonecoding.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private boolean anonymous;


    public Post(String title, String content, String imageUrl,
                User user, Category category, boolean anonymous){
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.user = user;
        this.category = category;
        this.anonymous = anonymous;
    }

}
