package com.example.blog.entity; // 패키징 인데 JAVA 특성이라 뭔지 모름

import jakarta.persistence.*; // 파이썬 import 이건 entity, column쓸려고 
import java.time.LocalDateTime; // 이건 밑에 글 작성 시점 저장

@Entity // 이게 JPA쓴다는 말임
public class Post { //클래스 이름 대문자 해야돼요

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id 필드는 PK임 뒤에 제너 뭐시기는 숫자세기
    private Long id;
  
    @Column(length = 30) //그냥 심심해서 길이제한 걸어봤음
    private String title; //private죠? 제목은 클래스 내부에서만 근데 friend나 뭐 함수 쓰면 되죠

    @Column(columnDefinition = "TEXT") //Column은 근데 프론트엔드 아닌가 여튼 글 내용임 TEXT는 타입
    private String content; //사실 여기가 내용임

    private LocalDateTime createdAt; //칼럼인데 children같은건가 봐요 이건 작성된 시간 저장용

    // 글 작성 시점 자동 저장                                 
    @PrePersist //JPA 쓰는 건데 몰라요 
    public void prePersist() { // 대충 시간 저장하기 위한 매서드죠?
        this.createdAt = LocalDateTime.now(); //저거 쓰면 현재시각으로 넣어줌
    }

    // Getter/Setter //얘네들 그냥 get뭐시기 함수임 private접근용
    public Long getId() { return id; } //글번호임

    public String getTitle() { return title; } //제목 조회

    public void setTitle(String title) { this.title = title; } //set함수죠? 수정용임

    public String getContent() { return content; } //내용 조회

    public void setContent(String content) { this.content = content; } //내용 수정

    public LocalDateTime getCreatedAt() { return createdAt; } //작성시간 조회임
}
