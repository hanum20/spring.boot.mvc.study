# SPRING WEB MVC 08 - URI 패턴

@PathVariable

```java
@GetMapping("/events/{id}")
@ResponseBody
public Event getEvent(@PathVariable Integer id){
    Event event = new Event();
    event.setId(id);
    return event;
}
```

* Integer를 `Optional<Integer>`로 감싸면, `require = false `한 것과 비슷하다



@MatrixVariable

```java
@GetMapping("/events/{id}")
@ResponseBody
public Event getEvent(@PathVariable Integer id, @MatrixVariable String name){
    Event event = new Event();
    event.setId(id);
    event.setName(name);
    return event;
}
```

* Test code

  ```java
  @Test
  public void getEvent() throws Exception {
      mockMvc.perform(get("/events/1;name=hanum"))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(jsonPath("id").value(1));
  }
  ```

  * `;`뒤의 값은 `key=value`가 된다.
  * 여기서 주의할 점은 url로 요청을 받을 때, `;`을 자동으로 없애버린다는 것이다. 따라서 spring boot에서 추가 설정이 필요하다.

* WebConfig

  ```java
  @Configuration
  public class WebConfig implements WebMvcConfigurer {
      @Override
      public void configurePathMatch(PathMatchConfigurer configurer) {
          UrlPathHelper urlPathHelper = new UrlPathHelper();
          urlPathHelper.setRemoveSemicolonContent(false);
          configurer.setUrlPathHelper(urlPathHelper);
      }
  }
  ```

  * 위와 같이 설정하여, `;`을 제거하지 않도록 한다.