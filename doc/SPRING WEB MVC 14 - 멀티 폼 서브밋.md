# SPRING WEB MVC 14 - 멀티 폼 서브밋

> @SessionAttributes를 이용하여, 멀티 폼 서브밋을 구현할 수 있다.

**Controller**

```java
@Controller
@SessionAttributes("event")
public class SampleController {

    @GetMapping("/events/form/name")
    public String eventsFormName(Model model){
        model.addAttribute("event", new Event());
        return "/events/form-name";
    }
    
    //session의 값도 바인딩을 받음
    @PostMapping("/events/form/name")
    public String createFormNameSubmit(@Validated @ModelAttribute Event event,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/events/form-name";
        }

        return "redirect:/events/form/limit";
    }

    @GetMapping("/events/form/limit")
    public String eventsFormLimit(@ModelAttribute Event event, Model model){
       // model.addAttribute("event", event);
        return "/events/form-limit";
    }

    //session의 값도 바인딩을 받음
    @PostMapping("/events/form/limit")
    public String createFormLimitSubmit(@Validated @ModelAttribute Event event,
                                       BindingResult bindingResult,
                                        SessionStatus sessionStatus){
        if(bindingResult.hasErrors()){
            return "/events/form-limit";
        }
        //sessionStatus.setComplete();
        return "redirect:/events/list";
    }
    
    @GetMapping("/events/list")
    public String getEvents() {
        return "/events/list";
    }
}
```



**view**

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a th:href="@{/events/form}">Create New Event</a>
<div th:unless="${#lists.isEmpty(event)}">
    <ul th:each="event: ${event}">
        <p th:text="${event.Name}">Event Name</p>
    </ul>
</div>

</body>
</html>
```

