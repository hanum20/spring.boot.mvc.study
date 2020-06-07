# SPRING WEB MVC 09 - 요청 매개변수

**@RequestParam**

* 요청 매개변수에 들어있는 단순 타입의 데이터를 메소드 야규먼트로 받아올 수 있다.

* 값이 반드시 있어야 한다.

  * `require=false` 또는` Optional`로 부가적인 설정 가능

* String이 아닌 값들은 컨버전을 지원한다.

* `Map<String, String>` 또는 `MultiValueMap<String, String>`으로 모든 매개변수를 받아올 수 있다.

* 애노테이션은 생략 가능하다.

  ```java
  // 생략 전
  public Event getEvent(@RequestParam String name){}
  
  // 생략 후
  public Event getEvent(String name){}
  ```



**예제**

```java
@PostMapping("/events")
@ResponseBody
public Event getEvent(@RequestParam String name,
                      @RequestParam Integer limit){
    Event event = new Event();
    event.setLimit(limit);
    event.setName(name);
    return event;
}
```



**테스트**

```java
@Test
public void getEvent() throws Exception {
    mockMvc.perform(post("/events")
                    .param("name","hanum")
                    .param("limit","20"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("name").value("hanum"));
}
```



**결과**

```
MockHttpServletResponse:
           Status = 200
    Error message = null
          Headers = [Content-Type:"application/json"]
     Content type = application/json
             Body = {"id":null,"name":"hanum","limit":20}
    Forwarded URL = null
   Redirected URL = null
          Cookies = []
```

