# SPRING WEB MVC - 요청 맵핑하기 HTTP METHOD



```java
@Controller
public class SampleController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
```

* 기본적으로 모든 RequestMethod에 대해 매핑된다.



**특정 method만 매핑**

```java
@Controller
public class SampleController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
```



**여러 특정 method에 매핑**

```java
@Controller
public class SampleController {

    @RequestMapping(value = "/hello", method = {RequestMethod.GET, RequestMethod.PUT})
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
```

