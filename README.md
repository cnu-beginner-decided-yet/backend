# Blog Backend Project

## 프로젝트 개요
본 프로젝트는 **CNU 2025 Fall 입문 프로젝트**로 진행된  
Spring Boot 기반 블로그 서비스 백엔드 개발 프로젝트입니다.

블로그 서비스에 필요한 기본적인 REST API를 구현하고,  
JWT 기반 인증을 적용하여 실제 서비스 구조를 경험하는 것을 목표로 합니다.

---

## 기술 스택
- Backend: Spring Boot
- ORM: JPA 
- Database: H2
- Authentication: JWT
- Build Tool: Gradle 
- Version Control: Git / GitHub

---

## 개발 정보
- 개발 기간: 2025년 9월 19일 ~ 2025년 12월 21일  
- 개발 인원: 5명 + 1명 (멘토)

---

## 개발 목표
- Spring Boot로 기본적인 백엔드 API 구현
- RESTful API 설계 및 CRUD 기능 구현
- JWT 기반 인증/인가 적용
- 블로그 서비스 핵심 기능 완성

---

## 구현 기능

### 회원 / 인증 (JWT)
- 회원가입
- 로그인
- 비밀번호 변경
- JWT 토큰 발급 및 검증
- 인증이 필요한 API 접근 제어

### 사용자 정보
- 내 정보 조회
- 내 프로필 수정
- 다른 유저 프로필 조회
- 내가 작성한 게시글 목록 조회

### 게시글
- 게시글 생성
- 전체 게시글 조회
- 게시글 상세 조회 (ID 기준)
- 게시글 수정
- 게시글 삭제
- 게시글 검색
  - 제목 키워드 검색
  - 내용 키워드 검색
  - 태그 기반 검색

### 좋아요
- 게시글 좋아요 추가
- 게시글 좋아요 취소
- 특정 게시글 좋아요 개수 조회
- 특정 게시글 좋아요 전체 조회
- 특정 유저가 누른 좋아요 전체 조회

### 카테고리
- 카테고리 생성
- 전체 카테고리 조회
- 카테고리 상세 조회
- 카테고리 수정
- 카테고리 삭제
- 카테고리별 게시글 조회

### 댓글
- 댓글 생성
- 댓글 수정
- 댓글 삭제
- 전체 댓글 조회
- 게시글별 댓글 조회
- 유저별 댓글 조회

---

## 페이지 구성 

### 로그인 / 회원가입 페이지
- 로그인 화면

<img width="2640" height="1468" alt="image" src="https://github.com/user-attachments/assets/ced7279d-bf7c-4f3f-8fef-018d1532ca42" />

- 회원가입 화면

<img width="2640" height="1468" alt="image" src="https://github.com/user-attachments/assets/4c89a14e-9129-4e82-976c-9c1aba021340" />



### 게시글 페이지
- 게시글 목록 페이지

<img width="1315" height="704" alt="image" src="https://github.com/user-attachments/assets/1b8d2ce4-c70f-45ac-b892-8b96ca4ba487" />


- 게시글 상세 페이지

<img width="2640" height="2074" alt="image" src="https://github.com/user-attachments/assets/2fe707c9-6ecf-4ea2-93c2-3df1f531d0c9" />

- 게시글 작성 페이지

<img width="2640" height="2262" alt="image" src="https://github.com/user-attachments/assets/aca7f7c4-50b3-4ad1-bfcf-4d003f3491ee" />


### 댓글 기능
- 게시글 댓글 조회 화면 및 댓글 작성 화면

<img width="1315" height="729" alt="image" src="https://github.com/user-attachments/assets/097ca913-7986-4a51-b0ae-8c931f87f8f3" />


### 카테고리

- 카테고리별 게시글 조회 화면

<img width="2640" height="1554" alt="image" src="https://github.com/user-attachments/assets/8adf058f-f77b-4096-8a28-5191fbc16e12" />


---

## 향후 개발 계회
- 프론트엔드 React 또는 Next.js 기반으로 전환
- 댓글 대댓글 기능 추가
- 게시글 공개 / 비공개 기능 추가

---

## 프로젝트를 통해 배운 점
- Spring Boot 기반 백엔드 구조 이해
- REST API 설계 경험
- JWT 인증 흐름 및 보안 개념 이해
- 팀 프로젝트 협업 및 Git 활용 경험


