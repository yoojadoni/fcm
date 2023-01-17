
//fire base SDK 참고 하여 작성
let firebaseConfig = {
    apiKey: "",
    authDomain: "",
    projectId: "",
    storageBucket: "",
    messagingSenderId: "",
    appId: ""
};
firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();

messaging.onMessage(function(payload){
  console.log('onMessage: ', payload);
  var title = payload.notification.title;
  var options = {
    body: payload.notification.body,
    icon: payload.notification.icon
  };
  var notification = new Notification(title,options);
});

//FCM을 통해 생성된 token값 알아내기
messaging.requestPermission()
    .then(function(){
        return messaging.getToken();
    })
    .then(function(token){
        console.log(token);
    })
    .catch(function(arr){
        console.log("arr="+arr);
    });
