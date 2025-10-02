@Service                                                          //뭐라카는지 모르겠어요
public class PostService {                                        //클래s선언

    private final PostRepository postRepository;                   //이건 했죠? DB랑 연결

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;                       //이건 근데 진짜 무슨 소린지 모르겠어
    }

    // 글 작성
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // 글 목록 조회
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    // 글 상세 조회
    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }
}
