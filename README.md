# firebase-web
### fcm push web version

FCM이란
메시지를 전송할 수 있는 플랫폼이다.


<div> 
  FCM의 경우 https와 localhost인 경우만 지원이 가능.
</div>

### FCM의 동작방식
1. 유저가 페이지 접속 
2. FCM 알림을 위한 허용여부 요청 알림생성(차단시 FCM받을수 없음)
3. 허용시 해당 브라우저 사용하는 유저에게 FCM Token 생성
4. 서버에 token 정보를 전달 후 저장.
5. 서버에 메시지 발생 요청시 token 정보를 사용하여 메시지 발송처리.

### 이 프로젝트의 경우 프론트쪽에서 로그인 후 Token 정보를 받았다는 가정하에 token 정보 업데이트 후 실제 메시지발송이 가능합니다.

### Admin SDK 설정
<div>
구글 firebase프로젝트에서 firebase Admin SDk를 통해 새 비공개 키를 생성하여 생성된 파일을 프로젝트내에 넣어줍니다.
<img src="https://github.com/yoojadoni/firebase-web/blob/master/SDK.png" width="50%" height="20%"/>
</div>

### Service Worker 설정
웹 푸시를 구현하기 위해서는 서비스 워커를 설정해야 합니다.
서비스워커 스크립트 파일은 public 폴더에 firebase-message-sw.js 라는 이름으로 따로 생성해주어야 합니다.
위에서 생성한 SDK을 참고하여 firebase-message-sw.js에 설정
importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-app.js'); importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-messaging.js');
// Initialize Firebase
var config = {
    apiKey: "",
    authDomain: "",
    projectId: "",
    storageBucket: "",
    messagingSenderId: "",
    appId: ""
};
firebase.initializeApp(config);

const messaging = firebase.messaging();

messaging.setBackgroundMessageHandler(function(payload){
    const title = "Hello World";
    const options = {
            body: payload.data.status
    };

    return self.registration.showNotification(title,options);
});

### 테스트 방법
1. main.html에서 token 생성처리 및 console을 통해 token 조회가능
<img src="https://github.com/yoojadoni/firebase-web/blob/master/token.png" width="100%" height="20%"/>

2. API서버를 통해 token 정보 업데이트
<img src="https://github.com/yoojadoni/firebase-web/blob/master/token_update.jpg" width="50%" height="20%"/>

3. 메시지 발송요청
<img src="https://github.com/yoojadoni/firebase-web/blob/master/send.jpg" width="50%" height="20%"/>

4. 결과
<img src="https://github.com/yoojadoni/firebase-web/blob/master/fcm_success.png" width="50%" height="20%"/>
