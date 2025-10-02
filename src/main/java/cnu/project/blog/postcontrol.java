@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 글 작성
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    // 글 목록 조회
    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    // 글 상세 조회
    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }
}
// 끄아아아ㅏ아아앙아ㅏ아아아ㅏ아아아아ㅏㅏㄱ
