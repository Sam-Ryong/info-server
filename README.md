# 📝 Samryong Blog Server

## 📌 소개
Samryong Blog Server는 블로그 게시글 관리 및 방문자 수 집계를 위한 Spring Boot 기반의 RESTful API 서버입니다. 게시글을 CRUD할 수 있으며, 방문자 수를 추적하는 기능을 제공합니다.

## 🚀 주요 기능
- **게시글 관리**
  - 전체 게시글 조회
  - 특정 게시글 조회
  - 게시글 생성
  - 게시글 수정
  - 게시글 삭제
- **방문자 수 집계**
  - 하루 단위로 방문자 수를 추적
  - 방문 여부에 따라 쿠키를 활용하여 중복 방문 방지

## 🏗️ 기술 스택
- **백엔드**: Spring Boot, Spring Web, Lombok
- **기타**: Jakarta Servlet, JSON Processing

## 📂 프로젝트 구조
```
samryong-blogserver/
├── src/
│   ├── main/
│   │   ├── java/samryong/blogserver/
│   │   │   ├── controller/  # API 컨트롤러
│   │   │   ├── model/       # 엔티티 모델
│   │   │   ├── service/     # 비즈니스 로직
│   │   │   ├── repository/  # 데이터 액세스
│   │   ├── resources/       # 설정 파일
│   ├── test/                # 테스트 코드
├── README.md
├── pom.xml                  # Maven 프로젝트 설정
```

## 🔧 API 엔드포인트

### 📌 게시글 API (`/api/posts`)
| 메서드 | 엔드포인트  | 설명 |
|--------|------------|------|
| `GET`  | `/api/posts` | 모든 게시글 조회 |
| `GET`  | `/api/posts/{id}` | 특정 게시글 조회 |
| `POST` | `/api/posts` | 새 게시글 생성 |
| `PUT`  | `/api/posts/{id}` | 게시글 수정 |
| `DELETE` | `/api/posts/{id}` | 게시글 삭제 |

### 📌 방문자 API (`/api/visit`)
| 메서드 | 엔드포인트  | 설명 |
|--------|------------|------|
| `GET`  | `/api/visit` | 방문자 수 조회 및 증가 |

## 🛠️ 실행 방법
### 1️⃣ 프로젝트 클론
```sh
git clone https://github.com/사용자명/samryong-blogserver.git
cd samryong-blogserver
```

### 2️⃣ 빌드 및 실행
```sh
./mvnw spring-boot:run
```
또는
```sh
mvn spring-boot:run
```

### 3️⃣ API 테스트
로컬 서버가 실행되면 아래 주소에서 API를 호출할 수 있습니다.
```
http://localhost:8080/api/posts
http://localhost:8080/api/visit
```
