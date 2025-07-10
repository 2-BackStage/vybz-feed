# 🎵 VYBZ Feed Service

> **VYBZ 플랫폼의 피드/공지/릴스/팬피드 관리 마이크로서비스**  
> 다양한 피드 타입(Notice, Reels, FanFeed, About)의 생성, 수정, 삭제, 동기화, Kafka 이벤트 발행을 담당하는 Spring Boot 기반 서비스입니다.

---

## 🛠 Tech Stack

| 구분         | 기술/버전                |
| ------------ | ----------------------- |
| **Language** | Java 17                 |
| **Framework**| Spring Boot 3.4.5       |
| **Database** | MongoDB, MySQL (JPA)    |
| **Message**  | Apache Kafka            |
| **Service Discovery** | Netflix Eureka Client |
| **Docs**     | Swagger/OpenAPI 3.0     |
| **Build**    | Gradle 8.4, Docker      |

**Architecture Pattern**  
- Layered Architecture  
- Domain-Driven Design (DDD)  
- Event-Driven Architecture  

---

## 📋 서비스 목록

| 서비스명           | 설명                        | 언어      | 상태      |
|--------------------|----------------------------|-----------|-----------|
| Feed Service       | 피드/공지/릴스/팬피드 관리  | Java 17   | ✅ Active |
| Notice Feed        | 공지사항 피드 관리          | Java 17   | ✅ Active |
| Reels Feed         | 릴스 피드 관리              | Java 17   | ✅ Active |
| FanFeed            | 팬 피드 관리                | Java 17   | ✅ Active |
| About Feed         | 소개 피드 관리              | Java 17   | ✅ Active |

---


## 🚀 Quick Start

### Prerequisites
- Java 17+
- Gradle 8.4+
- MongoDB
- Apache Kafka
- (선택) MySQL, Eureka Server

### Local Development

```bash
# 1. 프로젝트 클론
git clone https://github.com/2-BackStage/vybz-feed.git
cd vybz-feed

# 2. 빌드 및 실행 (로컬)
./gradlew clean build -x test
docker-compose up -d

# 3. API 문서 (Swagger)
# http://localhost:8080/swagger-ui/index.html
```

---

## 📁 프로젝트 구조 (Layered Architecture)

```text
vybz-feed/
├── build.gradle
├── Dockerfile
├── gradle/
│   └── wrapper/
├── gradlew
├── gradlew.bat
├── settings.gradle
├── src/
│   └── main/
│       ├── java/
│       │   └── back/
│       │       └── vybz/
│       │           └── feed_service/
│       │               ├── common/         # 🛠 공통 유틸, 예외, 설정, 엔티티
│       │               ├── feed/
│       │               │   ├── presentation/    # 💡 Controller
│       │               │   ├── application/     # ⚙️ Service
│       │               │   ├── domain/          # 🧩 Domain Model
│       │               │   ├── vo/              # 🧾 VO (값 객체)
│       │               │   ├── dto/             # 📦 DTO
│       │               │   └── infrastructure/  # 🗄 Repository
│       │               ├── kafka/          # 🔄 Kafka 이벤트/프로듀서/설정
│       │               └── FeedServiceApplication.java
│       └── resources/   # 설정 파일 (application.yml 등)
└── ...
```

---

## 🏗️ 레이어별 설명

- **Presentation Layer** (💡): API 요청/응답 담당 (Controller)
- **Application Layer** (⚙️): 비즈니스 로직, 트랜잭션/흐름 제어
- **Domain Layer** (🧩): 핵심 도메인 모델(엔티티, 도메인 서비스 등)
- **VO** (🧾): 불변 값 객체
- **Infrastructure Layer** (🗄): DB, 외부 API, Kafka 등 인프라 연동
- **DTO** (📦): 계층 간 데이터 전달 객체
- **Common** (🛠): 공통 유틸리티, 예외, 설정, 엔티티
- **Kafka** (🔄): Kafka 이벤트, 프로듀서, 설정

---

## 🔧 주요 기능

- **피드 타입별 관리**
  - Notice: 공지사항 피드 등록/수정/삭제
  - Reels: 릴스 피드 등록/수정/삭제
  - FanFeed: 팬 피드 등록/수정/삭제
  - About: 소개 피드 등록/수정/삭제
- **Kafka 이벤트 발행** (피드 생성/수정/삭제 등)
- **Cursor 기반 페이지네이션**
- **Swagger 기반 API 문서**
- **실시간 데이터 동기화**

---

## 🎯 API 엔드포인트 예시

<details>
<summary>Notice Feed</summary>

- `POST /api/v1/feed/notice` : 공지 등록  
- `PUT /api/v1/feed/notice/{noticeId}` : 공지 수정  
- `DELETE /api/v1/feed/notice/{noticeId}` : 공지 삭제  
</details>

<details>
<summary>Reels Feed</summary>

- `POST /api/v1/feed/reels` : 릴스 등록  
- `PUT /api/v1/feed/reels/{reelsId}` : 릴스 수정  
- `DELETE /api/v1/feed/reels/{reelsId}` : 릴스 삭제  
</details>

<details>
<summary>FanFeed</summary>

- `POST /api/v1/feed/fan` : 팬피드 등록  
- `PUT /api/v1/feed/fan/{fanFeedId}` : 팬피드 수정  
- `DELETE /api/v1/feed/fan/{fanFeedId}` : 팬피드 삭제  
</details>

<details>
<summary>About Feed</summary>

- `POST /api/v1/feed/about` : 소개 등록  
- `PUT /api/v1/feed/about/{aboutId}` : 소개 수정  
- `DELETE /api/v1/feed/about/{aboutId}` : 소개 삭제  
</details>

---

## 🔄 Kafka 이벤트

- about-create-event
- about-update-event
- fanfeed-create-event
- fanfeed-update-event
- notice-create-event
- notice-update-event
- reels-create-event
- reels-update-event
- feed-delete-event

---

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

## 📞 Contact

- **Project Link**: https://github.com/2-BackStage/vybz-feed
- **Team**: VYBZ Development Team

---

> Made with ❤️ by VYBZ Team 