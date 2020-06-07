package com.example.demo;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice(assignableTypes = {EventController.class, EventApi.class})
public class BaseController {
    @ExceptionHandler({EventException.class, RuntimeException.class})
    public String eventErrorhandler(EventException exception, Model model) {
        model.addAttribute("message", "Runtime error");
        return "error";
    }

    @InitBinder("event")
    public void initEventBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
        webDataBinder.addValidators(new EventValidator());
    }

    @ModelAttribute("categories")
    public List<String> categories(Model model){
        List<String> list = new ArrayList<>();
        list.add("study");
        list.add("test");
        return list;
    }
}
