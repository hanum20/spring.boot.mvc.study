# SPRING WEB MVC 02 - URI 패턴



특정 여러 URL 매핑

```java
@Controller
public class SampleController {

    @GetMapping({"/hello", "/hi"})
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
```

<br>



`? : 한 글자`

```java
@Controller
public class SampleController {

    @GetMapping("/hello?")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
```

<br>

`* : 여러 글자`

```java
@Controller
public class SampleController {

    @GetMapping("/hello/*")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
```

<br>

`** : 여러 Path`

```java
@Controller
public class SampleController {

    @GetMapping("/hello/**")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
```

```java
@Controller
@RequestMapping("/hello")
public class SampleController {

    @GetMapping("/**")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
```

<br>

