# Othello
## Othello Client
<div class="pull-right">  업데이트 :: 2018.07.27 </div><br>

---

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [Othello Client](#othello-client)
	* [Othello Client](#othello-client-1)
		* [환경](#환경)
			* [기본환경](#기본환경)
			* [라이브러리](#라이브러리)
		* [주요기능](#주요기능)
		* [추가기능](#추가기능)
		* [03.](#03)

<!-- /code_chunk_output -->

### 환경

#### 기본환경

- Max OS X
- Java 1.8 기준
- Java FX
- IntelliJ

#### 라이브러리

- JsonParser
- Junit

### 주요기능

- 멀티룸 구조
- 싱클톤 구조
- 큐를 이용한 데이터 전송 (Socket)
- 최소단위 패킷전달
- 불변변수 상수항으로 공통관리 관리
- 함수형인터페이스구현 및 적용
- Controller 활용 (JavFX)
- TableView Adapter Binding 처리
- GridPane Adapter Binding 처리

### 추가기능

- Error 처리
- Junit 테스트 적용
- 전역 변수로 문자열 관리 (다국어)
- fxml파일 @images/@strings 처리방법
- 바이트단위의 프로토콜정의 (비트연산) <- Json을 쓰지말고
- 바이트계산을 통한 패킷계산
- 서버 캐싱 플러싱 vs 실시간 서비스 <- 실시간 업데이트를 정해둔 시간에 업데이트
- 부하 테스트 ( 스트레스 테스트 )
- 패킷 큐에 대한 취소 패킷 ( 클라에서 로딩처리 )

---

**Created by SDM**

==작성자 정보==

e-mail :: jm921106@naver.com || jm921106@gmail.com

github :: https://github.com/moonscoding
