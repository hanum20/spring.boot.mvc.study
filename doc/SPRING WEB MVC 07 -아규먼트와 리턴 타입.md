# SPRING WEB MVC 07 - 아규먼트와 리턴 타입



### 아규먼트

* `HttpServletRequest`, `HttpServletResponse`는 잘 사용하지 않음
  * Interceptor의 prehandle에서 사용됨
* `PushBuilder`
  * spring 5에서 추가 됨
  * 어떤 요청에 대해서 응답에서 이미지 url이 있을 경우, 이미지 url을 다시 요청하여 이미지 정보를 가져오는데, 이 기능을 사용하면 PushBuilder가 응답하기 전에 이런 데이터들을 응답에 미리 push하게된다.
* `HttpMethod`
  * 현재 요청의 method를 알 수 있다.
  * handler에 method를 지정하지 않으면, 다양한 method의 요청을 받게되는데, 이 떄 사용될 수 있다.
* `RequestPart`



### 리턴 타입

* `ResponseEntity`
  * RestAPI의 로직에 따라서 status code, 응답 헤더, 응답 본문을 다르게 하고 싶을 때 사용하게 될 것.
* `HttpHeaders`
  * 본문 없이 응답 헤더만
* `String`
  * html(thymleaf)를 찾는다.
  * view resolver
* `Model`
  * view의 이름을 유추해서 찾는다.(url에서부터)
  * model 정보만 준다. 