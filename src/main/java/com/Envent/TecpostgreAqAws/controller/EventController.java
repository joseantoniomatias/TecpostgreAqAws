package com.Envent.TecpostgreAqAws.controller;
import com.Envent.TecpostgreAqAws.dto.EventRequestDTO;
import com.Envent.TecpostgreAqAws.domain.event.Event;
import com.Envent.TecpostgreAqAws.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Event> create(@ModelAttribute EventRequestDTO body) {
        // Agora o Spring vai conseguir casar os campos do formulário com o DTO
        Event newEvent = this.eventService.createEvent(body);
        return ResponseEntity.ok(newEvent);
    }
    @GetMapping
    public ResponseEntity<List<Event>> getAll() {
        List<Event> events = this.eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
}