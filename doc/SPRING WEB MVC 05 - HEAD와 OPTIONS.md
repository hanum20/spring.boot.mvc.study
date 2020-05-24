# SPRING WEB MVC 05 - HEAD와 OPTIONS

> SPRING에서 별도로 제공하지 않아도 된다.



**HEAD**

* GET과 동일한 요청이지만... 위 HEAD을 REQUEST METHOD로 주게되면, 응답 본문을 제외하고 보내게된다.

**테스트**

```java
@Test
public void hello() throws Exception {
    mockMvc.perform(head("/hello")
                        .param("name","hanum"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk());
}
```

* ResponseBody가 없다.



**OPTIONS**

* 사용할 수 있는 HTTP METHOD를 제공한다.
* 서버 또는 특정 리소스가 제공하는 기능을 확인할 수 있다.
* 서버는 Allow 응답 헤더에 사용할 수 있는 HTTP METHOD를 제공한다.