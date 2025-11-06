package hello.blog_clonecoding.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User extends BaseTimeEntity{

    //null 값을 허용하지 않으려면, Column(nullable = false)
    //이 컬럼의 값이 테이블 내에서 고유하게 하려면, Column(unique = true)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private String organization;

    private String bio;

    private boolean publicProfile;

    public User(String email, String password, String nickname,
                String organization, String bio,
                boolean publicProfile){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.organization = organization;
        this.bio = bio;
        this.publicProfile = publicProfile;
    }

}
