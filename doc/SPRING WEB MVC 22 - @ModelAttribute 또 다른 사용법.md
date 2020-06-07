# SPRING WEB MVC 22 - @ModelAttribute 또 다른 사용법

* @RequestMapping을 사용한 핸들러 메소드의 아규먼트에 사용하기

  * 파라메터로 받은 값들은 컨버징하여 객체로 바꾸어 주었었음

* @Controller 또는 @ControllerAdvice를 사용한 클래스에서 모델 정보를 초기화 할 때 사용.

* @RequestMapping과 같이 사용하면 해당 메소드에서 리턴하는 객체를 모델에 넣어 준다.

  * RequestToViewNameTranslator

  

**Controller**

```java
//    @ModelAttribute
//    public void categories(Model model){
//        List<String> list = new ArrayList<>();
//        list.add("study");
//        list.add("test");
//        model.addAttribute("categories", list);
//    }

    @ModelAttribute("categories")
    public List<String> categories(Model model){
        List<String> list = new ArrayList<>();
        list.add("study");
        list.add("test");
        return list;
    }
```

* 두 가지 방식으로 사용 가능하다.
* 주로 컨트롤러의 헨들러들이 공유하는 속성이 있을 경우 사용한다.