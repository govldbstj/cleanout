## 프론트엔드 파트

### 1. 폴더 구조

-   `components` : 컴포넌트들을 모아놓은 폴더입니다. `atoms`가 가장 작은 단위이고, `molecules`는 `atoms`를 조합한 것입니다.
-   `context` : 전역 상태를 관리하는 폴더입니다.
-   `controllers` : 각 페이지에서 서버와 통신하는 부분을 모아놓은 폴더입니다.
    **실질적인 서버 통신 로직은 여기에 있습니다.**
    `data/Api.js`를 활용해 API를 호출하고 데이터를 가공해 `screens`에 있는 페이지에 전달합니다.
-   `data` : 서버와 더욱 편리하게 통신하기 위해 만든 함수/클래스들을 모아놓은 폴더입니다.
-   `navigations` : 네비게이션을 관리하는 폴더입니다.
-   `screens` : 각 페이지를 모아놓은 폴더입니다.
-   `styles` : 스타일(색상 등)을 관리하는 폴더입니다.

### 2. API 호출 구조

-   `screen` -> `controller` -> `data/Api.js` -> [ 서버 ] -> `data/Api.js`-> `controller` -> `screen`
-   `data/Api.js`와 각 `controller`는 `data/Result.js`의 형태로 데이터를 반환합니다. 자세한 사항은 해당 파일의 주석을 참고해주세요.
-   API 호출에 대한 설명은 [#9](https://github.com/govldbstj/Mississipi/pull/9)에도 있습니다.

### 3. 개선이 필요한 부분

1. js의 fetch가 타임아웃을 지정할 수 없는 관계로, 서버가 꺼져있으면 로딩을 5분 동안 바라보셔야 합니다...
2. 로딩 화면을 현재 `navigations/Stack.js`에 구현에 두었는데, 그리 좋은 방법은 아닌 것 같습니다. 추후 개선이 필요합니다.
3. 현재 앱을 켤 때마다 로그인을 해야 합니다. 자동 로그인 기능이 있으면 좋을 것 같습니다.
4. 주의 사항에 쓸 말을 정해야합니다.

### 4.기타

1. Mac의 IOS Simulator에서는 이미지 선택 기능이 작동하지 않습니다. 테스트시 참고해주세요.
