# SpringBoot-Project-이게뭐약?
스프링 부트 + 파싱 영양제 추천 사이트

## 🖥️ 프로젝트 소개
영양제 검색과 성별, 연령별 분석을 통한 맞춤 서비스를 위한 웹페이지입니다.
<br>

## 🕰️ 개발 기간
* 2023.05.31 ~ 2023.07.04 

### 🧑‍🤝‍🧑 맴버구성
 #### 🐯 팀장: 이정현 
 - **Front-end** : 로그인, 영양제 검색, 영양제 정보 관리
 - **Back-end** : 로그인, 영양제 추천, 통합 테스트, 리뷰를 통한 핵심 키워드 분석, DB 설계, 데이터 수집
 #### 🐶 팀원: 김용원 :
 - **Front-end** : 회원가입, 마이페이지, 회원정보 수정, 영양제 상세
 - **Back-end** : 영양제 조회, 추가,수정, 삭제, 고유번호 중복 검사
 #### 🐼 팀원: 송원기 : 
 - **Front-end** : 메인 페이지, 비밀번호 찾기, 내가찜한 목록, 검색결과, 영양제 추가, 수정
 - **Back-end** : 영양제 검색

### ⚙️ 개발 환경
#### `Back-end` : 
- **Java 11**
- **MySQL 8.2**
- **IDE** : Eclipse 4.24.0
- **Springboot** : Spring Web, Spring Data JDBC, MySQL Driver, Thymeleaf, Lombok
- **Database** : MySQL
- **Gradle** : 7.6.1 Groovy
#### `Data-Analysis` :  
- **python 3.10.9**
- **IDE** : Jupyter notebook
- **Library** : Scikit Learn, Kiwi

## 📌 주요 기능
#### 로그인  
- DB값 검증
- ID찾기, PW찾기

#### 회원가입  
- 회원정보 저장

#### 마이 페이지  
- 회원정보 변경
- 찜 목록

#### 영양제 검색  
- 검색 결과

#### 관리자 페이지 
- 영양제 추가, 삭제, 수정

## 🛠️ 추후 작업
- 복용 일정 관리 : 사용자의 영양제 복용 일정 관리하는 기능을 추가하고 일별, 월별, 연도별 그래프를 시각화하여 표현
- 리팩토링
      - 찜 기능 : ajax를 이용한 비동기식 전송
      - 회원가입, 영양제 정보 추가 페이지에서 유효성 및 중복 검사
      - 반응형 웹
