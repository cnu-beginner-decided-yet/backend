package cnu.project.blog.user;

import jakarta.persistence.*; // JPA 어노테이션
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter // Lombok : 이 클래스 모든 필드에 Getter 자동 생성
@Setter // Lombok : Setter 자동 생성
@NoArgsConstructor // Lombok : 기본 생성자 자동 생성
@Entity // 데이터베이스 테이블과 매핑 (JPA)
@Table(name = "users") // 데이터베이스의 "users" 테이블과 연결

public class User {

    @Id // 이 필드가 기본 키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 알아서 ID 1씩 증가
    private Long id;

    @Column(nullable = false, unique = true) // null 안됨, 중복 안됨
    private String email;

    @Column(nullable = false) // null 안됨
    private String password;

    @Column(nullable = false, unique = true) // null 안됨, 중복 안됨
    private String nickname;

    @Column // 특별한 제약 없음
    private String organization;

    @Column(columnDefinition = "TEXT") // text 타입
    private String bio;

    @Column(name = "is_public")
    boolean isPublic = true;

    @CreationTimestamp // 데이터가 생성될 때 현재 시간 자동 저장
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * 개인 정보 수정 메서드 💡
     * 이 메서드를 호출하면 JPA의 Dirty Checking을 통해 트랜잭션 종료 시 DB에 자동 반영됩니다.
     */
    public void updateProfile(String nickname, String organization, String bio) {
        // null 체크를 통해 DTO에서 넘어온 값이 null이 아닐 경우에만 업데이트하도록 처리하는 것이 더 안전합니다.
        // 여기서는 UserService에서 필드가 모두 포함된 DTO를 사용했으므로 간단히 처리합니다.
        this.nickname = nickname;
        this.organization = organization;
        this.bio = bio;
    }
}
