# Daelimi

this repository is part of Daelimi Project  
본 레포지토리는 대림이 프로젝트(<https://github.com/Hod0ri/Daelimi>)의 안드로이드 클라이언트용 레포지토리 입니다.  
커밋 메시지는 `[ 작업 영역 ] 메시지`로 올려주세요.

```Daelimi
    //예시
    [ XMl ] 메시지 박스 완성
    [ JAVA ] api서버 연결
```

## 화면 구성 요소

<p align="center">
  <img src="https://user-images.githubusercontent.com/89181586/157015720-6c1bd3e7-ec93-4ba8-8984-5e1cc7c623ee.png">
  <img width="250" height="400" src="https://user-images.githubusercontent.com/89181586/157021189-4520cbce-ae6e-4b4f-8baf-9804db4546c6.png">
  <img width="250" height="400" src="https://user-images.githubusercontent.com/89181586/157242851-de607c26-d078-4cde-963c-77507c3c5c12.png">

</p>

##

```text
1) 메시지 창[id=edtMessage]
      대림대학교에 알고싶은 정보또는 궁금한 정보를 작성하는 곳이다.

2) 전송 버튼[id=fabSend]
      메시지 창에 적혀있는 내용을 전송한다.

3) FAQ봇 메뉴[id=menuDaelimi]
      FAQ봇과 질문을 주고 받을수 있는 영역으로 넘어갑니다.

4) 스마트캠퍼스 메뉴[id=menuSmartcapus]
      대림대학교 스마트캠퍼스로 연결되며 앱이 설치되지 않을시 마켓으로 연결됩니다.

5) 에브리 타임 메뉴[id=menuEverytime]
      에브리 타임으로 연결되며 앱이 설치되지 않을시 마켓으로 연결됩니다.

6) 문의 사이트 메뉴[id=menuInquiry]
      대림이 오류 또는 문의를 할 수 있는 영역으로 넘어갑니다.

7) 문의 사이트[id=wvView]
      대림이 오류에대한 제목과 내용을 작성하신 후 전송을 누르면 문의가 등록됩니다. 
```

## 참조 사이트 및 문서

1. 챗봇:  
   How to Create a Chatbot in Android with BrainShop API? (<https://www.geeksforgeeks.org/how-to-create-a-chatbot-in-android-with-brainshop-api/>)  

2. Retrofit2 :  
   [안드로이드] Retrofit2 '레트로핏' - 기본 사용법 (<https://jaejong.tistory.com/33>),  
   [안드로이드] Retrofit2 기본 사용법2 -'GET/POST/PUT/DELETE' (<https://jaejong.tistory.com/38?category=873924>)

3. 어플리케이션 스플래시 화면:  
   [Android Studio] 스플래시 화면 만들기 (앱 타이틀 화면, 로고 화면) (<https://anywaydevlog.tistory.com/24>)

4. 어플리케이션 하단 네비뷰:  
   Android Studio - BottomNavigationView위젯으로 하단 메뉴탭 만들기 (<https://bubblebubble.tistory.com/5>)
   NavigationBarView.OnItemSelectedListener | Android Developers (<https://developer.android.com/reference/com/google/android/material/navigation/NavigationBarView.OnItemSelectedListener?hl=ko>)

5. 어플리케이션 이미지뷰 GIF 추가:  
   안드로이드 GIF 추가하기 (<https://pickersoft.net/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-GIF-%EC%B6%94%EA%B0%80%ED%95%98%EA%B8%B0>)