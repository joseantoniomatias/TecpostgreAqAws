package com.Envent.TecpostgreAqAws.repository;
import com.Envent.TecpostgreAqAws.domain.event.Event;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Garante que use o seu Postgres
@ActiveProfiles("test")
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    @DisplayName("Deveria persistir o evento no banco e gerar um UUID")
    void saveEventSuccess() {
        Event event = new Event();
        event.setTitle("Workshop Java");
        event.setDescription("Aula de testes");
        event.setRemote(true);
        event.setDate(new Date());
        event.setCity("Salvador");
        event.setState("BA");

        Event savedEvent = this.eventRepository.save(event);

        assertNotNull(savedEvent.getId());
    }
}