# 🎨 ArtTab

> 당신의 인생에 예술을 담아보세요.  
> 작가정보부터 그림 구입,판매까지  
> 세상에서 가장 가까운 아트페어 Art Tab이 여러분을 찾아 갑니다.

ArtTab은 `그림을 공유하고 싶어하는 사람들을 위한 SNS`에요. 여러 그림을 한눈에 감상하기에 최적화된 서비스를 제공합니다.

&nbsp;

## 1. 프로젝트 개요

### 1) 기간

    2022. 1. 10.~2022. 2. 18.(기획 2주, 설계 1주, 개발 3주)

### 2) 인원

    6명

### 3) 주요 기능

- **그림 피드(feed)**
  - 사람들이 올린 그림들을 모아 한 번에 볼 수 있다.(메인)
  - `feed`? 내가 올린 정보들(그림)을 나의 팔로워에게 전달하는 것!
- **그림 관리**
  - 사용자가 그린 그림을 등록, 수정, 삭제할 수 있다.
  - 댓글로 작가와 소통할 수 있다.
  - 좋아하는 작가를 팔로우할 수 있다.
  - 좋아하는 작품에 좋아요를 할 수 있다.
- **마이 갤러리**
  - 사용자가 좋아요한 작품으로 나만의 갤러리를 꾸밀 수 있다.
- **사용자 관리**
  - 카카오 로그인을 할 수 있다.

### 4) 기술스택

- **Frontend**: **Vue.js, TypeScript, SCSS**
- **Backend**: Spring Boot, JPA, MySQL

![image](https://user-images.githubusercontent.com/55578809/154552324-5d8f8611-c94e-4c6d-b5b8-21210778957f.png)

### 4-1) CI/CD

![image](https://user-images.githubusercontent.com/55578809/154552139-e673862e-f44e-48b2-9678-daad73a705a1.png)

### 5) 역할 💜 `Frontend`

#### Navbar, 그림 상세 조회, 갤러리 꾸미기 기능 구현

- 좋아요, 팔로우 버튼, 토스트 메시지 및 비밀번호 입력창 등을 다른 곳에서도 사용할 수 있도록 구현했습니다.
- 어디서나 ArtTab을 편리하게 사용할 수 있도록 반응형으로 구현했습니다.

#### 회원가입, 로그인 기능 구현

- 정규표현식을 사용해서 이메일 형식을 검사했습니다.
- Kakao 로그인 API를 이용하여 token을 받아와 소셜 로그인을 구현했습니다.
- 로그인 상태를 Session Storage로 관리하고, 로그인 여부에 따라 페이지 접근을 제한(router guard)하도록 구현했습니다.

&nbsp;

## 2. 기능 소개 🖼️

### 1) 카카오 로그인

![ezgif com-gif-maker (3)](https://user-images.githubusercontent.com/55578809/154606515-9aeea3ea-b5b1-4f90-9a18-8c70ebc283dd.gif)

### 2) 그림 피드(feed) - 메인

![ezgif com-gif-maker (2)](https://user-images.githubusercontent.com/55578809/154603822-1d981abb-2f10-41e2-beb7-371f1fb19218.gif)

### 3) 그림 등록

![insert artwork (1)](https://user-images.githubusercontent.com/55578809/154597267-7ba9839e-e169-4bc3-adc5-9564bbc54607.gif)

### 4) 그림 삭제

![delete artwork](https://user-images.githubusercontent.com/55578809/154598732-5f863a51-113d-4481-b2e8-dfc18dec32ed.gif)

### 5) 마이페이지

![updateInfo](https://user-images.githubusercontent.com/55578809/154598730-50b3b3da-b28f-4bb2-97eb-15453bab55c3.gif)

### 6) 마이갤러리

![my gallery2 (1)](https://user-images.githubusercontent.com/55578809/154597931-14f173eb-9397-4344-905e-8ca7a330042a.gif)

### 7) 댓글, 좋아요

![댓글,좋아요](https://user-images.githubusercontent.com/55578809/154598208-aec68b88-aa6f-4913-8c1a-155636105edf.gif)

## 3. Team 〽️

| <img src="https://lab.ssafy.com/uploads/-/system/user/avatar/3706/avatar.png?width=400" width="230px;" alt="오재문"/> | <img src="https://secure.gravatar.com/avatar/d971e1b10959c5b19d0c6d98069b3d8d?s=800&d=identicon" width="230px;" alt="박창현"/> | <img src="https://secure.gravatar.com/avatar/ec47db8d724e462e92c510a548279e62?s=800&d=identicon" width="230px;" alt="박해인"/> | <img src="https://secure.gravatar.com/avatar/45331f87f9cc8dd8fb042c0ff310d666?s=800&d=identicon" width="230px;" alt="배나영"/> | <img src="https://secure.gravatar.com/avatar/710ed197c74c72677243a59a3581afb6?s=800&d=identicon" width="230px;" alt="백철연"/> | <img src="https://secure.gravatar.com/avatar/98f66880dc16c2436bfe4369e878449e?s=800&d=identicon" width="230px;" alt="신미래"/> |
| :-------------------------------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------: |
|                                       [오재문](https://lab.ssafy.com/tph01198)                                        |                                            [박창현](https://lab.ssafy.com/pch1656)                                             |                                       [박해인](https://lab.ssafy.com/haein.hannah.park)                                        |                                           [배나영](https://lab.ssafy.com/qoskdud15)                                            |                                            [백철연](https://lab.ssafy.com/backcy1)                                             |                                            [신미래](https://lab.ssafy.com/sml6209)                                             |
|                                                        Backend                                                        |                                                            Backend                                                             |                                                            Frontend                                                            |                                                            Backend                                                             |                                                            Frontend                                                            |                                                            Frontend                                                            |
|                                                         팀장                                                          |                                                          git, 인프라                                                           |                                                             동영상                                                             |                                                          backend 팀장                                                          |                                                           jira 담당                                                            |                                                           front 팀장                                                           |

&nbsp;

### ▶ 팀 규칙

- 내가 틀릴 수 있다.

- 개인의 이슈는 모두의 이슈

- 정확한 리액션, 확실한 피드백

- 1일 1진행사항 작성

- 남의 일은 없다.

&nbsp;

## 4. 프로젝트 진행

### 🍸 설계도

- [와이어프레임](https://www.figma.com/file/lfozAybsvjkkbNYzhUdi4l/%EB%B0%98%EB%B0%98%EB%A7%90%EB%A7%88%EB%8B%88?node-id=0%3A1)
- [ERD](https://www.erdcloud.com/d/gNfGpb3YLzHWH2cnw)
- [api 설계](https://documenter.getpostman.com/view/5813163/UVXqFYVJ)
- [회의 내용](https://haeinpark.notion.site/2-1-fdfb2b569362442ab99f07bb4a5aa04a)

### 🍳 Git Convention

- `feat`: 새로운 기능 추가(a new feature)
- `fix`: 버그 수정(a bug fix)
- `docs`: 문서 수정(changes to documentation)
- `style`: 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우 (formatting, missing semi colons, etc; no code change)
- `refactor`: 코드 리팩토링(refactoring production code), 로직과 관계 없는 소스 코드 개선
  불필요한 파일 및 코드 삭제
  라우트 설정 파일 변경
- `test`: 테스트 코드, 리펙토링 테스트 코드 추가(adding tests, refactoring test; no production code change)
- `chore`: 빌드 업무 수정, 패키지 매니저 수정, 라이브러리, 환경 설정 파일(package.json, .config 등) 수정 등
  updating build tasks, package manager configs, etc; no production code change
- `design`: CSS 등 사용자 UI 디자인 변경
- `comment`: 필요한 주석 추가 및 변경
- `rename`: 파일 또는 폴더명을 수정하거나 옮기는 작업만 수행한 경우
- `remove`: 파일을 삭제하는 작업만 수행한 경우
- `HOTFIX`: 급하게 치명적인 버그를 고쳐야 하는 경우

&nbsp;

## 5. 🔎 배포 명령어

### backend

`cd backend`

`chmod 777 gradlew`

`./gradlew clean bootjar`

`docker build -t arttab ./`

`docker ps -f name=arttab -q | xargs --no-run-if-empty docker container stop`

`docker container ls -a -f name=arttab -q | xargs -r docker container rm`

`docker run -d -p 9091:8080 -it -e TZ=Asia/Seoul -v /home/arttab/img:/img --name arttab arttab`

</br>

### frontend

`cd frontend`

`npm install`

`npm run build`

</br>
