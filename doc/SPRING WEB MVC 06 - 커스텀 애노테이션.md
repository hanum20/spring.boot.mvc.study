# SPRING WEB MVC 06 - 커스텀 애노테이션

**조합(Composed) 애노테이션**

> 한개 혹은 여러 메타 애노테이션을 조합해서 만은 애노테이션.
>
> > @GetMapping

<br>

<br>



**메타 애노테이션**

> 다른 애노테이션 위에서 사용할 수 있는 애노테이션
>
> ```JAVA
> @Target({ElementType.METHOD})
> @Retention(RetentionPolicy.RUNTIME)
> @Documented
> @RequestMapping(
>     method = {RequestMethod.GET}
> )
> public @interface GetMapping {
>     ...
> }
> ```

<br>

<br>

**@Retention**

> Java 설명...
>
> 애노테이션 정보를 언제까지 유지할 것인가?
>
> ```java
> @Retention(RetentionPolicy.RUNTIME)
> @RequestMapping(method = RequestMethod.GET, value = "/hello")
> public @interface GetHelloMapping {
> }
> ```
>
> Retention 정책을 Runtime으로 지정하지 않으면, Dafault가 Class이므로, 클래스 로더가 클래스를 메모리로 로딩하는 순간. 애노테이션 정보가 사라진다.

<br>

<br>

**@Target(ElementType.METHOD)**

> 메소드에 해당 애노테이션을 사용하도록 설정한다.
>
> ```java
> @Target(ElementType.METHOD)
> @Retention(RetentionPolicy.RUNTIME)
> @RequestMapping(method = RequestMethod.GET, value = "/hello")
> public @interface GetHelloMapping {
> }
> ```

<br>

<br>

**@Documented**

> 해당 애노테이션을 사용한 코드의 문서에 해당 애노테이션에 대한 정보 표기 결정

