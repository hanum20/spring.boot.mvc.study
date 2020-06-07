# SPRING WEB MVC 18 - MultipartFile

* 파일 업로드시 사용하는 메소드 아규먼트
* MultipartResolve 빈이 설정되어 있어야 사용할 수 있다.
  * 스프링 부트는 자동 설정이 해 줌(`MultipartAutoConfiguration`)
  * properties에서 설정 함.
    * MultipartProperties
* POST multipart/form-data 요청에 들어있는 파일을 참조할 수 있다.
*  List < MultipartFile>아큐먼트로 여러 파일을 참조할 수도 있다.



**form**

```html
<form method="POST" enctype="multipart/form-data" action="#" th:action="@{/file}">
    File: <input type="file" name="file"/>
    <input type="submit" value="Upload"/>
</form>
```



**Controller**

```java
@PostMapping("/file")
public String uploadFile(@RequestParam MultipartFile file,
    RedirectAttributes attributes) {
    
    String message = file.getOriginalFilename() + " is uploaded.";
    System.out.println(message);
    attributes.addFlashAttribute("message", message);
    
    return "redirect:/events/list";
}
```

