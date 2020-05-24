# SPRING WEB MVC 03 - 미디어 타입

```java
@Controller
public class SampleController {

    @RequestMapping(
            value = "/hello",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
```

` consumes = MediaType.APPLICATION_JSON_VALUE`

* Json 형태로 요청을 받을 것임

`produces = MediaType.TEXT_PLAIN_VALUE`

* 응답의 형식은 TEXT PLAIN일 것이다.