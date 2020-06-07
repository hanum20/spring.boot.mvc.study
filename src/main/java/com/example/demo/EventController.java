package com.example.demo;

        import org.springframework.http.HttpHeaders;
        import org.springframework.http.MediaType;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.BindingResult;
        import org.springframework.validation.annotation.Validated;
        import org.springframework.web.bind.WebDataBinder;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.bind.support.SessionStatus;
        import org.springframework.web.servlet.mvc.support.RedirectAttributes;

        import javax.servlet.http.HttpSession;
        import javax.validation.Valid;
        import java.time.LocalDateTime;
        import java.util.ArrayList;
        import java.util.List;

@Controller
@SessionAttributes("event")
public class EventController {
    @GetMapping("/events/form/name")
    public String eventsFormName(Model model){
        throw new EventException();
//
//        model.addAttribute("event", new Event());
//        return "/events/form-name";
    }
    //session의 값도 바인딩을 받음
    @PostMapping("/events/form/name")
    public String createFormNameSubmit(@Validated @ModelAttribute Event event,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/events/form-name";
        }

        return "redirect:/events/form/limit";
    }

    @GetMapping("/events/form/limit")
    public String eventsFormLimit(@ModelAttribute Event event, Model model){
       // model.addAttribute("event", event);
        return "/events/form-limit";
    }

    //session의 값도 바인딩을 받음
    @PostMapping("/events/form/limit")
    public String createFormLimitSubmit(@Validated @ModelAttribute Event event,
                                        BindingResult bindingResult,
                                        SessionStatus sessionStatus,
                                        RedirectAttributes attributes){
        if(bindingResult.hasErrors()){
            return "/events/form-limit";
        }

        sessionStatus.setComplete();

        /*
        attributes.addAttribute("name", event.getName());
        attributes.addAttribute("limit", event.getLimit());
        */

        attributes.addFlashAttribute("newEvent", event);
        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model,
                            HttpSession httpSession) {
        LocalDateTime visitTime = (LocalDateTime) httpSession.getAttribute("visitTime");
        System.out.println(visitTime);

        /*
        Event newEvent = new Event();
        newEvent.setName(name);
        newEvent.setLimit(limit);
        */
        Event newEvent = (Event) model.asMap().get("newEvent");

        List<Event> eventList = new ArrayList<>();
        eventList.add(newEvent);

        model.addAttribute(eventList);

        return "/events/list";
    }
}
