# SPRING WEB MVC 10 - @ModelAttribute

**예제**

```java
@PostMapping("/events")
@ResponseBody
public Event getEvent(@ModelAttribute Event event, BindingResult bindingResult){
    System.out.println("======================");
    bindingResult.getAllErrors().forEach(c -> {
        System.out.println(c.toString());
    });
    return event;
}
```

* `@ModelAttribute`는 생략 가능하다.
* Type과는 다른 값이 파라메터로 들어올 경우 `bind exception`을 발생시킨다. 
  * 위와 같이 파라메터로 BindingResult를 주면, Exception을 발생시키지 않는다.



**Test**

```java
@Test
public void getEvent() throws Exception {
    mockMvc.perform(post("/events")
                .param("name","hanum")
                .param("limit","dsffdsdfs"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("name").value("hanum"));
}
```





**@Valid**

* Request Params를 객체의 맴버변수에 바인딩을 하고나서 유효성 검사를 실시한다.
* 이 Validation에 대한 에러 역시 BindingResult에 쌓이게 된다.