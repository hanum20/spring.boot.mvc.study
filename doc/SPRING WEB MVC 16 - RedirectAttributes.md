# SPRING WEB MVC 16 - RedirectAttributes

* 리다이렉트 할 때 기본적으로 Model에 들어있는 기본 타입 데이터는 URI 쿼리 매개변수에 추가된다.
  * 스프링 부트에서는 이 기능이 기본적으로 비활성화된다.
  * Ignore-default-model-on-redirect으로 활성화할 수 있다.
    * RedirectAttributes 사용 시, 설정 안해도 됨(?)
* 원하는 값만 리다이렉트 할 때, `RedirectAttributes`를 이용할 수 있다.

```java
@PostMapping("/events/form/limit")
public String createFormLimitSubmit(@Validated @ModelAttribute Event event,
                                    BindingResult bindingResult,
                                    SessionStatus sessionStatus,
                                    RedirectAttributes attributes){
    if(bindingResult.hasErrors()){
        return "/events/form-limit";
    }

    sessionStatus.setComplete();
    attributes.addAttribute("name", event.getName());
    attributes.addAttribute("limit", event.getLimit());
    return "redirect:/events/list";
}

@GetMapping("/events/list")
public String getEvents(@RequestParam String name,
                        @RequestParam Integer limit,
                        Model model,
                        HttpSession httpSession) {
    LocalDateTime visitTime = (LocalDateTime) httpSession.getAttribute("visitTime");
    System.out.println(visitTime);

    Event newEvent = new Event();
    newEvent.setName(name);
    newEvent.setLimit(limit);

    List<Event> eventList = new ArrayList<>();
    eventList.add(newEvent);

    model.addAttribute(eventList);

    return "/events/list";
}
```

* `@SessionAttributes`와 `@ModelAttribute`를 함께 사용할 때는 조심해야 한다.
  * 만약 SessionAttributes의 속성 이름과 ModelAttribute이 받는 이름이 같으면
  * Prameter로 넘어온 값을 받기 전에, Session에 있는 속성을 찾아 먼저 가져오려고 시도한다.
  * 만약, 값이 없다면 오류를 발생시킨다.