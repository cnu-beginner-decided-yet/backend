package hello.blog_clonecoding.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parent;


    public Comment(String content, Post post, User user){
        this.content = content;
        this.post = post;
        this.user = user;
        this.parent = null;
    }

    public Comment(String content, Post post, User user, Comment parent){
        this.content = content;
        this.post = post;
        this.user = user;
        this.parent = parent;
    }



}
