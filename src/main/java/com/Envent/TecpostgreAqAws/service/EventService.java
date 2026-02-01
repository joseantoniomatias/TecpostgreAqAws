package com.Envent.TecpostgreAqAws.service;

import com.Envent.TecpostgreAqAws.service.S3Service;
import com.Envent.TecpostgreAqAws.domain.event.Event;
import com.Envent.TecpostgreAqAws.dto.EventRequestDTO;
import com.Envent.TecpostgreAqAws.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private S3Service s3Service; // Precisamos injetar o serviço que criamos!

    public Event createEvent(EventRequestDTO data) {
        Event newEvent = new Event();

        String imgUrl = null;

        // CORREÇÃO: Chama o s3Service para fazer o upload real
        if (data.image() != null && !data.image().isEmpty()) {
            imgUrl = this.s3Service.uploadImg(data.image());
        }

        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setRemote(data.remote());
        newEvent.setCity(data.city());
        newEvent.setState(data.state());

        // CORREÇÃO: Seta a URL que veio da AWS no objeto
        newEvent.setImgUrl(imgUrl);

        if (data.date() != null) {
            newEvent.setDate(new Date(data.date()));
        }
        newEvent.setImgUrl(imgUrl);
        return repository.save(newEvent);
    }



    public List<Event> getAllEvents() {
        return repository.findAll();
    }
}

