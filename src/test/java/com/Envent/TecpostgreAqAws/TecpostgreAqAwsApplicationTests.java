package com.Envent.TecpostgreAqAws;

import com.Envent.TecpostgreAqAws.domain.event.Event;
import com.Envent.TecpostgreAqAws.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TecpostgreAqAwsApplicationTests {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void testSaveEvent() {
        Event event = new Event();
        event.setTitle("Teste de Conexão");
        event.setRemote(true);
        event.setDate(new java.util.Date());

        // Salva no banco
        Event savedEvent = eventRepository.save(event);

        // Verifica se o ID não é nulo
        assertNotNull(savedEvent.getId());
        System.out.println("MÁGICA ACONTECEU! ID Gerado kk: " + savedEvent.getId());
    }
}