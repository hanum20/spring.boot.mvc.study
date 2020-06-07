# SPRING WEB MVC 13 - @SessionAttributes

* `HttpSession`을 이용하여 session에 속성을 추가할 수 있다.

  ```java
  @GetMapping("/events/form")
  public String eventsForm(Model model, HttpSession httpSession){
      Event newEvent = new Event();
      newEvent.setLimit(50);
      model.addAttribute("event", newEvent);
      httpSession.setAttribute("event", newEvent);
      return "/events/form";
  }
  ```

* Class 상단에 `@SessionAttributes`를 추가하여 Session을 관리할 수도 있다.

  ```java
  @Controller
  @SessionAttributes("event")
  public class SampleController {
  
      @GetMapping("/events/form")
      public String eventsForm(Model model){
          Event newEvent = new Event();
          newEvent.setLimit(50);
          model.addAttribute("event", newEvent);
          return "/events/form";
      }
  }
  ```

  * `event`라는 속성을 `@SessionAttributes`에 줄 경우, `model.addAttribute("event", newEvent);` 처럼, `@sessionAttributes`와 같은 이름의 속성을 추가할 때.

    * Model에 추가된 속성의 값이 Session에도 추가된다.

  * 홈쇼핑의 장바구니나, 여러 화면에 걸쳐서 데이터를 생성해야 하는 경우에 사용될 수 있다.

  * session에 추가되었던 값들은 다음과 같이 `SessionStatus`로 초기화를 할 수 있다.

    ```java
    @PostMapping("/events")
    public String createEvent(@Validated @ModelAttribute Event event,
                              BindingResult bindingResult,
                              SessionStatus sessionStatus){
        if(bindingResult.hasErrors()){
            return "/events/form";
        }
    
        // 세션 초기화
        sessionStatus.setComplete();
        return "/events/list";
    }
    ```

  * Controller 내부에서만 유효하다