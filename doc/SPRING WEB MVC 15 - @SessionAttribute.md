# SPRING WEB MVC 15 - @SessionAttribute

> `@SessionAttributes`와 혼동하지 말 것(끝의 `s`가 다름)!
>
> 하는 일도 많이 다름.

* **HTTP 세션**에 있는 값을 **참조할 때** 사용한다.(입출력 때 사용하는 것 아님...)
  * 일단 HttpSession과는 다르게 **타입 컨버젼을 자동**으로 지원해서 더 편리하다.
  * HTTP 세션에 데이터를 넣고 뺴고 싶은 경우에는 `HttpSession`을 사용할 것.
* `@SessionAttributes`와는 다르다
  * `@SessionAttributes`는 컨트롤러 내에서만 동작한다.
    * 즉, 컨트롤러 안에서 다루는 특정 모델 객체를 세션에 넣고 공유할 때 사용하는 것
  * `@SessionAttribute`는 컨트롤러 밖(인터셉터 같은..)에서 생성된 세션 데이터를 **접근**할 때 사용하는 것

```java
/*
* SessionAttributes와 다른 점
* - session에 직접 넣었기 때문에 HttpSession으로도 꺼내쓸 수 있다.
* */
@GetMapping("/events/list")
public String getEvents(@SessionAttribute LocalDateTime visitTime) {
    System.out.println(visitTime);
    return "/events/list";
}
```

* Session에 직접 넣었기 때문에, HttpSession으로도 꺼내쓸 수 있다.

  ```java
   @GetMapping("/events/list")
  public String getEvents(HttpSession httpSession) {
      LocalDateTime visitTime = (LocalDateTime) httpSession.getAttribute("visitTime");
      System.out.println(visitTime);
      return "/events/list";
  }
  ```

  * 리턴 값이 Object 형식으로 나오기 때문에, 형변환이 필요하다.

