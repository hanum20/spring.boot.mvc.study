# SPRING WEB MVC 12 - 폼 서브밋 에러 처리

> Validate, BindingResult를 이용하여 폼 서브밋의 에러를 처리해본다.



**예제**

Event

```java
public class Event {
    private Integer id;

    @NotBlank
    private String name;

    @Min(0)
    private Integer limit;
    
    ...
}
```

<br>

Controller

```java
@PostMapping("/events")
public String getEvent(@Validated @ModelAttribute Event event,
                       BindingResult bindingResult,
                       Model model){
    if(bindingResult.hasErrors()){
        return "/events/form";
    }

    List<Event> eventList = new ArrayList<Event>();
    eventList.add(event);
    model.addAttribute(eventList);
    return "/events/list";
}
```

* Validataion에서 오류가 발생하면, BindingResult에 쌓이게 된다.
* 오류가 있으면, 다시 `form`을 띄워준다.
* 오류가 없으면, `list`를 띄운다.
* 주의할 점
  * `list`를 띄우고 브라우저를 `refreash`를 하게되면, 중복 서브밋이 발생할 수 있다.
  * 따라서, 이런 경우를 방지하기 위해 `redirect`를 이용하게 된다.
  * `redirect` 방법은 여러가지가 있는데, 그 중 한가지는
    * `return "redirect:/events/list"`와 같이 prefix를 주는 것이다.

<br>

form

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="#" th:action="@{/events}" method="post" th:object="${event}">
    <p th:if="${#fields.hasErrors('limit')}" th:errors="*{limit}">Incorrect data</p>
    <p th:if="${#fields.hasErrors('name')}" th:errors="*{name}">Incorrect data</p>
    <input type="text" title="name" th:field="*{name}" />
    <input type="text" title="limit" th:field="*{limit}" />
    <input type="submit" value="Create" />
</form>

</body>
</html>
```

* 오류가 있을 경우, 관련 오류 문장을 출력한다.
* 자세한 문법은 document 확인할 것

