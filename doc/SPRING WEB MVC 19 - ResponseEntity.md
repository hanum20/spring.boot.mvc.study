# SPRING WEB MVC 19 - ResponseEntity

* 파일 리소스는 `ResourceLoader`를 사용한다.

* 파일 다운로드 응답 헤더

  * Content-Disposition : 사용자가 파일을 다운할 때 사용할 이름
  * Content-Type: 어떤 파일인가
  * Content-Length : 얼마나 큰 파일인지?

* 파일 종류 알아내는 방법 

  * **Tika** 사용

  * 종속성

    ```xml
    <dependency>
        <groupId>org.apache.tika</groupId>
        <artifactId>tika-core</artifactId>
        <version>1.24.1</version>
    </dependency>
    ```



**Controller**

```java
@GetMapping("/file/{filename}")
    public ResponseEntity<Resource> fileDownload(@PathVariable String filename) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + filename);
        File file = resource.getFile();

        // get Content-type
        Tika tika = new Tika();
        String type = tika.detect(file);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachement: filename= \"" + resource.getFilename() + "\"")
            .header(HttpHeaders.CONTENT_TYPE, type)
            .header(HttpHeaders.CONTENT_LENGTH, file.length() + "")
            .body(resource);
}
```



