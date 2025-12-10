package cnu.project.blog.user;

import cnu.project.blog.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import java.util.Optional;

@Service // 이 클래스는 스프링의 '서비스' 계층
@RequiredArgsConstructor // final이 붙은 필드들을 자동으로 주입(DI)하는 생성자
public class UserService {

    // 'final' 키워드는 이 객체가 한 번 주입되면 절대 바뀌지 않음
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * 회원가입 비즈니스 로직
     * @param requestDto 회원가입 요청 정보
     */
    @Transactional // "이 메서드 안의 모든 DB 작업은 하나의 '거래'로 묶입니다."
    public void signup(UserSignupRequestDto requestDto) {
        
        // 1. 이메일 중복 검사
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다."); // 현재 중복 메시지 출력 x
        }

        // 2. 비밀번호 암호화
        String rawPassword = requestDto.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // 3. DTO를 -> Entity로 변환 (DB에 저장할 'User' 객체 생성)
        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword); // 암호화된 비번을 저장
        user.setNickname(requestDto.getNickname());
        // isPublic, createdAt 등은 Entity의 기본값/설정(@CreationTimestamp)으로 자동 처리됨

        // 4. DB에 저장
        userRepository.save(user);
    }

    /**
     * 로그인 비즈니스 로직
     * @param requestDto 로그인 요청 정보 (이메일, 비밀번호)
     */
    @Transactional (readOnly = true) // 읽기 전용 트랜잭션
    public String login(UserLoginRequestDto requestDto){
        // 1. 이메일로 사용자 조회
        String email = requestDto.getEmail();
        User user = userRepository.findByEmail(email)
        .orElseThrow(()->new IllegalArgumentException("등록되지 않은 이메일입니다.")); // Optional 처리

        // 2. 비밀번호 일치 여부 확인
        String rawPassword = requestDto.getPassword();
        String encodedPassword = user.getPassword(); // DB에 저장된 암호화된 비밀번호

        // passwordEncoder.matches(평문 비밀번호, 암호화된 비밀번호) -> 일치하면 ture, 아니면 false
        if (!passwordEncoder.matches(rawPassword, encodedPassword)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 로그인 성공 -> JWT 토큰 생성 및 반환
        return jwtUtil.createToken(user.getEmail()); // 사용자 이메일로 토큰 생성
    }

    /**
     * JWT에서 추출된 이메일 (사용자 식별자)을 사용하여
     * DB에서 실제 User 엔티티를 찾고, 해당 User의 ID (Long)를 반환합니다.
     *
     * @param email JWT Payload의 subject에 저장된 사용자 이메일
     * @return User 엔티티의 ID (Long)
     * @throws IllegalArgumentException 해당 이메일의 사용자가 존재하지 않을 경우
     */
    public Long findUserIdByEmail(String email) {
        // 1. 이메일을 통해 User를 조회합니다. (JWT Subject = email로 가정)
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));

        // 2. User 엔티티에서 ID를 반환합니다.
        return user.getId();
    }

    /**
     * 이메일 (JWT Subject)을 사용하여 User 엔티티를 찾습니다.
     */
    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
    }

    /**
     * ID를 사용하여 User 엔티티를 찾습니다.
     */
    @Transactional(readOnly = true)
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
    }
    /**
     * 내 정보 조회 기능: 이메일로 User 엔티티를 찾고, UserResponseDto로 변환하여 반환합니다.
     */
    public UserResponseDto getUserInfoByEmail(String email) {
        User user = findUserByEmail(email);
        return UserResponseDto.from(user);
    }

    /**
     * 타인 프로필 조회 기능 (닉네임 기반) 💡
     * 닉네임으로 User 엔티티를 찾고, UserResponseDto로 변환하여 반환합니다.
     */
    public UserProfilePublicDto getUserInfoByNickname(String nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("User not found with nickname: " + nickname));
        // 💡 UserProfilePublicDto.from()을 사용하여 민감 정보를 제외하고 반환
        return UserProfilePublicDto.from(user);
    }

    /**
     * 개인 정보 수정 기능 (Transactional) 💡
     * 이메일을 통해 사용자를 찾고, DTO를 기반으로 정보를 업데이트합니다.
     */
    @Transactional // 수정 트랜잭션은 readOnly=false여야 합니다.
    public UserResponseDto updateUserInfo(String email, UserUpdateRequestDto updateDto) {
        User user = findUserByEmail(email);

        // 엔티티의 update 메서드를 사용하여 정보 수정 (User 엔티티에 해당 메서드가 있다고 가정)
        user.updateProfile(updateDto.getNickname(), updateDto.getOrganization(), updateDto.getBio());

        // (저장 로직은 JPA Dirty Checking에 의해 자동으로 처리되므로, 별도의 save 호출이 필요 없습니다.)

        // 수정된 정보를 DTO로 변환하여 반환
        return UserResponseDto.from(user);
    }
}