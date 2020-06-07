package com.example.demo;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventApi {

    @ExceptionHandler
    public ResponseEntity errorhandler() {
        return ResponseEntity.badRequest().body("can't create event as ...");
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody @Valid Event event, BindingResult bindingResult){

        // 요청의 헤더에 접근할 수 있다.
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(event);
    }
}
