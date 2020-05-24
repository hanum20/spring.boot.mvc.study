package com.example.demo;

        import org.springframework.http.HttpHeaders;
        import org.springframework.http.MediaType;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;

        import java.awt.*;

@Controller
public class SampleController {

    @GetHelloMapping
    @ResponseBody
    public String hello(){
        return "hello";
    }

    @GetMapping("/events")
    @ResponseBody
    public String events(){
        return "events";
    }

    @GetMapping("/event/{id}")
    @ResponseBody
    public String event(@PathVariable int id){
        return "event";
    }

    @PostMapping(
            value = "/events",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createEvent(){
        return "createEvent";
    }

    @DeleteMapping("/events")
    @ResponseBody
    public String deleteEvent(){
        return "deleteEvent";
    }
}
