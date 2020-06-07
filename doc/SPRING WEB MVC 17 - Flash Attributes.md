# SPRING WEB MVC 17 - Flash Attributes

* 주로 리다이렉트 시, 데이터를 전달 할 때 사용한다.
  * **데이터가 노출되지 않음**
  * **HttpSession에 객체를 담아놓는다.**
    - 한 번 사용하면, 사라진다. (1회용)
  * `addAttribute`는 uri path로 붙어버리기 때문에, string으로 변환 가능한 값만 넘길 수 있다.
    - 그런데, FlashAttribute는 **임의의 객체를 넘길 수 있다.** (가장 큰 장점)



**Controller**

```jaVA
@PostMapping("/events/form/limit")
public String createFormLimitSubmit(@Validated @ModelAttribute Event event,
                                    BindingResult bindingResult,
                                    SessionStatus sessionStatus,
                                    RedirectAttributes attributes){
    if(bindingResult.hasErrors()){
        return "/events/form-limit";
    }

    sessionStatus.setComplete();

    // HttpSession에 임의의 객체를 저장할 수 있다.
    attributes.addFlashAttribute("newEvent", event);
    return "redirect:/events/list";
}

@GetMapping("/events/list")
public String getEvents(Model model,
                        HttpSession httpSession) {
    LocalDateTime visitTime = (LocalDateTime) httpSession.getAttribute("visitTime");
    System.out.println(visitTime);
    
    // 이렇게 한 번 사용되면, HttpSession에서는 사라진다.
    Event newEvent = (Event) model.asMap().get("newEvent");

    List<Event> eventList = new ArrayList<>();
    eventList.add(newEvent);

    model.addAttribute(eventList);

    return "/events/list";
}
```

