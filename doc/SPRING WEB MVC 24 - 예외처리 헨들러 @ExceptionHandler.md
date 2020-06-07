# SPRING WEB MVC 24 - 예외처리 헨들러: @ExceptionHandler

* 특정 예외가 발생한 요청을 처리하는 핸들러를 정의한다.

  * REST API의 경우 응답 본문에 에러에 대한 정보를 담아주고, 상태 코드를 설정하려면 ResponseEntity를 주로 사용한다.

    ```java
    @ExceptionHandler
    public ResponseEntity errorhandler() {
        return ResponseEntity.badRequest().body("can't create event as ...");
    }
    ```

    * ResponseEntity는 응답에 대해서 커스텀을 할 수 있어서 RestAPI에서 많이 쓰인다.



**기본**

* Controller의 핸들러

  ```java
  @ExceptionHandler
  public String eventErrorhandler(EventException exception, Model model) {
      model.addAttribute("message", "event error");
      return "error";
  }
  ```

* EventException.class

  ```java
  public class EventException extends RuntimeException{
  }
  ```

  



**예외 처리 우선 순위**

```java
@ExceptionHandler
public String eventErrorhandler(EventException exception, Model model) {
    model.addAttribute("message", "event error");
    return "error";
}

@ExceptionHandler
public String RuntimeErrorhandler(RuntimeException exception, Model model) {
    model.addAttribute("message", "runtime error");
    return "error";
}
```

* 가장 구체적인 ExceptionHandler가 매핑된다.
* EventException이 발생하면, EventException에 대한 handler가 매핑될 것이다.



**여러 ExceptionHandler 다루기**

```java
@ExceptionHandler({EventException.class, RuntimeException.class})
public String eventErrorhandler(EventException exception, Model model) {
    model.addAttribute("message", "Runtime error");
    return "error";
}
```

* 애노테이션에 여러 Exception에 대한 클래스를 나열함으로써 여러 예외처리를 할 수 있다.
* 주의할 점은, 여러가지 Exception을 받기 위한 상위 타입을 아규먼트로 주어야 한다.
* 이 두 가지 에러가 발생하면, 이 헨들러가 처리할 것이다.