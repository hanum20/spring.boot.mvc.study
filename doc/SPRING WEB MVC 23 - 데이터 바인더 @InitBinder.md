# SPRING WEB MVC 23 - 데이터 바인더 @InitBinder

* 특정 컨트롤러에서 바인딩 또는 검증 설정을 변경하고 싶을 때 사용한다.



바인딩 설정

```java
@InitBinder
public void initEventBinder(WebDataBinder webDataBinder){
    webDataBinder.setDisallowedFields("id");
}
```

* 입력을 받더라도 binding하지 않게 한다.



포메터 설정

```java
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
private LocalDate startDate;
```

* 위 애노테이션이 인식되는 이유는, 관련된 포메터가 자동으로 등록되어있기 때문이다.
* 위 애노테이션에 의해서 포메터가 등록된다.
* 웹과 관련해서는 포메터를 주로 쓴다.



Validator 설정

```java
public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Event event = (Event) target;
        if(event.getName().equalsIgnoreCase("aaa")){
            errors.rejectValue("name", "wrongValue", "the value is not allowed");
        }
    }
}
```

```java
 @InitBinder
public void initEventBinder(WebDataBinder webDataBinder){
    webDataBinder.addValidators(new EventValidator());
}
```



특정 모델 객체에만 바인딩 또는 Validator 설정을 적용하고 싶은 경우

```
@InitBinder("event")
```





