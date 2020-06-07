# SPRING WEB MVC 25 - 전역 컨트롤러 : @(Rest)ControllerAdvice

**@ControllerAdvice, @RestControllerAdvice**

예외 처리, 바인딩 설정, 모델 객체를 모든 컨트롤러 전반에 걸쳐 적용하고 싶은 경우에 사용한다.

* @ExceptionHandler
* @InitBinder
* @ModelAttributes





**기본 예제**

```java
@ControllerAdvice
public class BaseController {
    @ExceptionHandler({EventException.class, RuntimeException.class})
    public String eventErrorhandler(EventException exception, Model model) {
        model.addAttribute("message", "Runtime error");
        return "error";
    }

    @InitBinder("event")
    public void initEventBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
        webDataBinder.addValidators(new EventValidator());
    }

    @ModelAttribute("categories")
    public List<String> categories(Model model){
        List<String> list = new ArrayList<>();
        list.add("study");
        list.add("test");
        return list;
    }
}
```

* 이 경우, 이런 핸들러들이 모든 컨트롤러에 적용이 된다.



**일부 컨트롤러에만 적용하기**

> 스프링 4.0부터 적용 범위를 결정할 수 있게 되었음

```java
@ControllerAdvice(assignableTypes = {EventController.class, EventApi.class})
public class BaseController {
    ...
}
```

