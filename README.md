# VYBZ Feed Service

VYBZ 플랫폼의 피드(About, FanFeed, Notice, Reels) 관리 및 이벤트 발행을 담당하는 마이크로서비스입니다.

## 📋 목차

-   [개요](#개요)
-   [기술 스택](#기술-스택)
-   [주요 기능](#주요-기능)
-   [프로젝트 구조](#프로젝트-구조)
-   [API 문서](#api-문서)
-   [설치 및 실행](#설치-및-실행)
-   [환경 설정](#환경-설정)
-   [피드 시스템](#피드-시스템)
-   [이벤트 처리](#이벤트-처리)
-   [아키텍처](#아키텍처)
-   [개발 가이드](#개발-가이드)
-   [트러블슈팅](#트러블슈팅)
-   [라이선스](#라이선스)
-   [팀](#팀)

---

## 🎯 개요

VYBZ Feed Service는 다음과 같은 기능을 제공합니다:

-   **피드 관리**: About, FanFeed, Notice, Reels CRUD 및 조회
-   **이벤트 발행**: Kafka를 통한 피드 관련 이벤트 발행
-   **데이터 저장**: MongoDB를 통한 피드 데이터 저장
-   **Swagger**: API 문서 자동화

---

## 🛠 기술 스택

### Backend

![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![Apache Kafka](https://img.shields.io/badge/Apache_Kafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

### Infra

![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)
![Amazon EC2](https://img.shields.io/badge/Amazon_EC2-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white)
![Nginx](https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

### 협업

![Discord](https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

---

## 🚀 주요 기능

-   **피드 CRUD**: About, FanFeed, Notice, Reels 생성/수정/삭제/조회
-   **커서 기반 페이징**: MongoDB 기반 커서 페이징 지원
-   **Kafka 이벤트 발행**: 피드 생성/수정/삭제 시 이벤트 발행
-   **Swagger API 문서 제공**

---

## 📁 프로젝트 구조

```
src/main/java/back/vybz/feed_service/
├── common/                    # 공통 모듈
│   ├── config/               # 설정 클래스 (Mongo, Swagger 등)
│   │   ├── MongoConfig.java
│   │   ├── ObjectMapperConfig.java
│   │   └── SwaggerConfig.java
│   ├── entity/               # 공통 응답 엔티티
│   │   ├── BaseResponseEntity.java
│   │   └── BaseResponseStatus.java
│   ├── exception/            # 예외 처리
│   │   ├── BaseException.java
│   │   ├── BaseExceptionHandler.java
│   │   ├── BaseExceptionHandlerFilter.java
│   │   ├── AsyncExceptionHandler.java
│   │   └── BaseResponseStatus.java
│   └── util/                 # 유틸리티
│       └── CursorPage.java
├── feed/                      # 피드 도메인
│   ├── application/          # 서비스 계층
│   │   └── service/
│   │       ├── AboutService.java
│   │       ├── AboutServiceImpl.java
│   │       ├── FanFeedService.java
│   │       ├── FanFeedServiceImpl.java
│   │       ├── NoticeService.java
│   │       ├── NoticeServiceImpl.java
│   │       ├── ReelsService.java
│   │       └── ReelsServiceImpl.java
│   ├── domain/               # 도메인 모델
│   │   └── mongodb/
│   │       ├── Feed.java
│   │       ├── FeedFile.java
│   │       ├── FeedType.java
│   │       ├── FileType.java
│   │       ├── HumanType.java
│   │       ├── TaggedHuman.java
│   │       └── WriterType.java
│   ├── dto/                  # DTO 계층
│   │   └── request/
│   │       ├── RequestAddAboutDto.java
│   │       ├── RequestAddFanFeedDto.java
│   │       ├── RequestAddNoticeDto.java
│   │       ├── RequestAddReelsDto.java
│   │       ├── RequestUpdateAboutDto.java
│   │       ├── RequestUpdateFanFeedDto.java
│   │       ├── RequestUpdateNoticeDto.java
│   │       └── RequestUpdateReelsDto.java
│   ├── infrastructure/       # 리포지토리 계층
│   │   └── repository/
│   │       ├── AboutRepository.java
│   │       ├── AboutRepositoryCustom.java
│   │       ├── AboutRepositoryCustomImpl.java
│   │       ├── FanFeedRepository.java
│   │       ├── FanFeedRepositoryCustom.java
│   │       ├── FanFeedRepositoryCustomImpl.java
│   │       ├── NoticeRepository.java
│   │       ├── NoticeRepositoryCustom.java
│   │       ├── NoticeRepositoryCustomImpl.java
│   │       ├── ReelsRepository.java
│   │       ├── ReelsRepositoryCustom.java
│   │       └── ReelsRepositoryCustomImpl.java
│   ├── presentation/         # REST 컨트롤러
│   │   ├── AboutController.java
│   │   ├── FanFeedController.java
│   │   ├── NoticeController.java
│   │   └── ReelsController.java
│   └── vo/                   # VO 계층
│       └── request/
│           ├── RequestAddAboutVo.java
│           ├── RequestAddFanFeedVo.java
│           ├── RequestAddNoticeVo.java
│           ├── RequestAddReelsVo.java
│           ├── RequestUpdateAboutVo.java
│           ├── RequestUpdateFanFeedVo.java
│           ├── RequestUpdateNoticeVo.java
│           └── RequestUpdateReelsVo.java
├── kafka/                    # Kafka 이벤트 처리
│   ├── config/               # Kafka 설정
│   │   ├── CommonKafkaProducerConfig.java
│   │   └── ReelsSearchCreateEventConfig.java
│   ├── event/                # 이벤트 모델
│   │   ├── AboutCreateEvent.java
│   │   ├── AboutUpdateEvent.java
│   │   ├── FanFeedCreateEvent.java
│   │   ├── FanFeedUpdateEvent.java
│   │   ├── FeedDeleteEvent.java
│   │   ├── NoticeCreateEvent.java
│   │   ├── NoticeUpdateEvent.java
│   │   ├── ReelsCreateEvent.java
│   │   ├── ReelsSearchCreateEvent.java
│   │   └── ReelsUpdateEvent.java
│   └── producer/             # 이벤트 프로듀서
│       ├── CommonKafkaProducer.java
│       └── ReelsSearchCreateEventProducer.java
├── FeedServiceApplication.java # 메인 클래스
```

---

## 📚 API 문서

Swagger UI를 통해 API 문서를 확인할 수 있습니다:

-   **URL**: `http://localhost:8000/feed-service/swagger-ui/index.html`
-   **API 그룹**: FEED-SERVICE

---

## 🚀 설치 및 실행

### 1. 사전 요구사항

-   Java 17
-   Gradle 8.4+
-   Docker (선택사항)
-   MongoDB 6.0+
-   Kafka 3.0+

### 2. 로컬 실행

```bash
# 프로젝트 클론
git clone <repository-url>
cd vybz-feed

# Gradle 빌드
./gradlew clean build

# 애플리케이션 실행
./gradlew bootRun
```

### 3. Docker 실행

```bash
# Docker 이미지 빌드
docker build -t vybz-feed .

# Docker 컨테이너 실행
docker run -p 8000:8000 vybz-feed
```

---

## ⚙️ 환경 설정

### 주요 설정 파일

-   `application.yml`: 기본 설정

### 환경 변수 예시

```yaml
spring:
    data:
        mongodb:
            uri: mongodb://${MONGO_USERNAME}:${MONGO_PASSWORD}@${MONGO_HOST}:${MONGO_PORT}/${MONGO_DATABASE}
kafka:
    bootstrap-servers: ${KAFKA_SERVERS}
```

---

## 📝 피드 시스템

-   **피드 타입**: About, FanFeed, Notice, Reels
-   **커서 기반 페이징**: CursorPage 유틸리티 활용
-   **피드 파일 타입**: 이미지, 비디오 등 지원
-   **작성자/태그**: WriterType, TaggedHuman 등 도메인 모델 활용

---

## 📡 이벤트 처리

-   **Kafka 이벤트 발행**: 피드 생성/수정/삭제 시 이벤트 발행
-   **이벤트 모델**: AboutCreateEvent, NoticeUpdateEvent 등
-   **프로듀서**: CommonKafkaProducer, ReelsSearchCreateEventProducer

---

## 🏗 아키텍처

### 도메인 주도 설계 (DDD)

-   **Domain Layer**: 피드 도메인 모델과 비즈니스 로직
-   **Application Layer**: 서비스 로직과 유스케이스
-   **Infrastructure Layer**: MongoDB, Kafka 등 외부 시스템 연동
-   **Presentation Layer**: REST API 컨트롤러

### 마이크로서비스 패턴

-   **Event-Driven**: Kafka를 통한 비동기 이벤트 처리
-   **Stateless**: 상태 없는 서비스 설계

---

## 🔧 개발 가이드

-   **패키지 구조**: 도메인별 계층 분리
-   **네이밍**: 명확하고 일관된 네이밍 규칙
-   **예외 처리**: BaseException을 통한 통일된 예외 처리
-   **로깅**: Slf4j를 통한 구조화된 로깅

---

## 🚨 트러블슈팅

### MongoDB 연결 오류

```bash
# MongoDB 연결 확인
mongo mongodb://vybz:vybz1234@<탄력적 IP>:27020/vybz?authSource=admin
```

### Kafka 연결 오류

```bash
# Kafka 브로커 상태 확인
kafka-topics.sh --bootstrap-server <탄력적 IP>:10000 --list
```

### 로그 확인

```bash
# 애플리케이션 로그 확인
tail -f logs/application.log

# 에러 로그 확인
grep "ERROR" logs/application.log
```

---

## 📝 라이선스

이 프로젝트는 VYBZ 팀의 내부 프로젝트입니다.

---

## 👥 팀

-   **개발팀**: VYBZ Backend Team

---

**VYBZ Feed Service** - 피드 관리 및 이벤트 발행 서비스
