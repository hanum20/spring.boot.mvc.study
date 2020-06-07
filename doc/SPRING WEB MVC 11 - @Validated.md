# SPRING WEB MVC 11 - @Validated

**의존성 추가**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```



**@Valid와 @Validated**

* Request의 파라메터들을 복합 타입인 클래스의 맴버변수에 바인딩을 하고난 후, 미리 정의해둔대로 유효성을 검사한다.

* `@Validated`는 `@valid`에는 없는 그룹 기능이 있다.



**예제**

Event

```java
public class Event {

    interface ValidateLimit {}
    interface ValidateName {}

    private Integer id;

    @NotBlank(groups = ValidateName.class)
    private String name;

    @Min(value = 0, groups = ValidateLimit.class)
    private Integer limit;
    
    ...
}
```

<br>

Controller

```java
@PostMapping("/events")
@ResponseBody
public Event getEvent(@Validated(Event.ValidateLimit.class) @ModelAttribute Event event, BindingResult bindingResult){
    System.out.println("======================");
    bindingResult.getAllErrors().forEach(c -> {
        System.out.println(c.toString());
    });
    return event;
}
```

<br>

**Test**

```java
@Test
public void getEvent() throws Exception {
    mockMvc.perform(post("/events")
                    .param("name","hanum")
                    .param("limit","-10"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("name").value("hanum"));
}
```

<br>

**결과**

```
Field error in object 'event' on field 'limit': rejected value [-10]; codes [Min.event.limit,Min.limit,Min.java.lang.Integer,Min]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [event.limit,limit]; arguments []; default message [limit],0]; default message [must be greater than or equal to 0]
```

