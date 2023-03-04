<div align = center>
    <img src="https://user-images.githubusercontent.com/52804557/222876687-9b6489c9-e4ce-4902-aff2-482cda5afabe.png" />
</div>

# CLEAN OUT
> 대형 폐기물 자동화 처리 시스템</br>2023 Prometheus Startup Hackathon

<div>
  FE</br>
  <img src = "https://img.shields.io/badge/-ReactNative-blue"/>
  <img src = "https://img.shields.io/badge/-Expo-white"/>
  <img src = "https://img.shields.io/badge/-Javascript-yellow"/></br>
  BE</br>
  <img src = "https://img.shields.io/badge/-SpringBoot-green"/>
  <img src = "https://img.shields.io/badge/-SpringDataJPA-orange"/>
  <img src = "https://img.shields.io/badge/-H2-blue"/>
  <img src = "https://img.shields.io/badge/-MySQL-lightgrey"/>
  <img src = "https://img.shields.io/badge/-awsEC2-blue"/>
  <img src = "https://img.shields.io/badge/-awsRDS-9cf"/></br>
  ML</br>
  <img src = "https://img.shields.io/badge/-pytorch-00498C"/>
  <img src = "https://img.shields.io/badge/-flask-00AAFF"/>
  <img src = "https://img.shields.io/badge/-ngrok-lightgrey"/>
</div>

# 서비스 소개
> 클린아웃은 대형 폐기물을 쉽게 수거할 수 있는 서비스입니다.</br>폐기할 가구와 전자기기 등 대형 쓰레기를 사진 찍으면 머신러닝을 통해 각 물품에 대한 부가 금액을 책정합니다.</br>클린아웃은 다소 번거로운 기존의 스티커 정책을 대체할 수 있는 서비스로, 쓰레기 등록과 수거자 매칭, 수거 예약과 방문 수거까지 해결할 수 있는 솔루션입니다.</br>기존의 지자체에서 운영하는 쓰레기 수거 서비스를 대체할 수 있는 G2B(Government To Business)의 특성을 띠는 서비스입니다.

# Directory
```
.
Mississipi
 ┣ ML
 ┃ ┗ app.py
 ┣ backend
 ┃ ┣ gradle
 ┃ ┃ ┗ wrapper
 ┃ ┃ ┃ ┣ gradle-wrapper.jar
 ┃ ┃ ┃ ┗ gradle-wrapper.properties
 ┃ ┣ src
 ┃ ┃ ┣ main
 ┃ ┃ ┃ ┣ java
 ┃ ┃ ┃ ┃ ┗ com
 ┃ ┃ ┃ ┃ ┃ ┗ backend
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ BackendApplication.java
 ┃ ┃ ┃ ┗ resources
 ┃ ┃ ┃ ┃ ┗ application.properties
 ┃ ┃ ┗ test
 ┃ ┃ ┃ ┗ java
 ┃ ┃ ┃ ┃ ┗ com
 ┃ ┃ ┃ ┃ ┃ ┗ backend
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ BackendApplicationTests.java
 ┃ ┣ .gitignore
 ┃ ┣ build.gradle
 ┃ ┣ gradlew
 ┃ ┣ gradlew.bat
 ┃ ┗ settings.gradle
 ┣ frontend
 ┃ ┣ assets
 ┃ ┃ ┣ anims
 ┃ ┃ ┃ ┣ clock.json
 ┃ ┃ ┃ ┗ trash.json
 ┃ ┃ ┣ adaptive-icon.png
 ┃ ┃ ┣ email.png
 ┃ ┃ ┣ favicon.png
 ┃ ┃ ┣ icon.png
 ┃ ┃ ┣ kakao.png
 ┃ ┃ ┗ splash.png
 ┃ ┣ src
 ┃ ┃ ┣ components
 ┃ ┃ ┃ ┣ atoms
 ┃ ┃ ┃ ┃ ┣ Button.js
 ┃ ┃ ┃ ┃ ┣ ImageButton.js
 ┃ ┃ ┃ ┃ ┣ Label.js
 ┃ ┃ ┃ ┃ ┣ Notice.js
 ┃ ┃ ┃ ┃ ┣ TextButton.js
 ┃ ┃ ┃ ┃ ┗ TextInput.js
 ┃ ┃ ┃ ┗ molecules
 ┃ ┃ ┃ ┃ ┣ AnimatingButton.js
 ┃ ┃ ┃ ┃ ┣ FormTextInput.js
 ┃ ┃ ┃ ┃ ┣ ScrollImageList.js
 ┃ ┃ ┃ ┃ ┗ StatusRow.js
 ┃ ┃ ┣ context
 ┃ ┃ ┃ ┣ Address.js
 ┃ ┃ ┃ ┣ Loading.js
 ┃ ┃ ┃ ┗ Login.js
 ┃ ┃ ┣ controllers
 ┃ ┃ ┃ ┣ LoginController.js
 ┃ ┃ ┃ ┣ ReservationController.js
 ┃ ┃ ┃ ┗ TrashRegisterController.js
 ┃ ┃ ┣ data
 ┃ ┃ ┃ ┣ Api.js
 ┃ ┃ ┃ ┗ Result.js
 ┃ ┃ ┣ navigations
 ┃ ┃ ┃ ┗ Stack.js
 ┃ ┃ ┣ screens
 ┃ ┃ ┃ ┣ register
 ┃ ┃ ┃ ┃ ┣ Email.js
 ┃ ┃ ┃ ┃ ┣ EmailRegister.js
 ┃ ┃ ┃ ┃ ┗ Kakao.js
 ┃ ┃ ┃ ┣ Address.js
 ┃ ┃ ┃ ┣ Login.js
 ┃ ┃ ┃ ┣ Main.js
 ┃ ┃ ┃ ┣ Register.js
 ┃ ┃ ┃ ┣ Reservation.js
 ┃ ┃ ┃ ┗ ReservationDetail.js
 ┃ ┃ ┣ styles
 ┃ ┃ ┃ ┗ colors.js
 ┃ ┃ ┗ App.js
 ┃ ┣ .gitignore
 ┃ ┣ .prettierrc.json
 ┃ ┣ App.js
 ┃ ┣ README.md
 ┃ ┣ app.json
 ┃ ┣ babel.config.js
 ┃ ┣ package-lock.json
 ┃ ┗ package.json
 ┗ README.md
```

 # ML
* 데이터셋</br>
생활 폐기물 이미지</br>
URL : https://aihub.or.kr/aihubdata/data/view.do?currMenu=115&topMenu=100&dataSetSn=140</br>
* 모델</br>
mobileNet</br>

