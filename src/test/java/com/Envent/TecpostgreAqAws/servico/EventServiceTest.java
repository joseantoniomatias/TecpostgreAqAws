package com.Envent.TecpostgreAqAws.servico;
import com.Envent.TecpostgreAqAws.service.S3Service;
import com.Envent.TecpostgreAqAws.domain.event.Event;
import com.Envent.TecpostgreAqAws.dto.EventRequestDTO;
import com.Envent.TecpostgreAqAws.repository.EventRepository;
import com.Envent.TecpostgreAqAws.service.EventService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)

public class EventServiceTest {


        @InjectMocks
        private EventService eventService;

        @Mock
        private EventRepository eventRepository;

        @Test
        @DisplayName("Deve mapear DTO para Entity corretamente")
        void shouldCreateEventWithAllFields() {
            // Criamos um DTO de exemplo com Salvador/BA
            EventRequestDTO dto = new EventRequestDTO("Titulo", "Desc", 1706544000L, "Salvador", "BA", true, "url",null);

            // Executamos a lógica
            eventService.createEvent(dto);

            // Verificamos se o Repository recebeu a Entity com os dados certos
            ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
            verify(eventRepository).save(eventCaptor.capture());

            Event savedEvent = eventCaptor.getValue();
            assertEquals("Salvador", savedEvent.getCity());
            assertEquals("BA", savedEvent.getState());
        }
    }

