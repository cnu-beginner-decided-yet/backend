import org.springframework.data.jpa.repository.JpaRepository;       // 이건 모름 그냥 넣어

public interface PostRepository extends JpaRepository<Post, Long> {     // <클래스, PK명> 넣으시면 돼요
}
