package com.Envent.TecpostgreAqAws.repository;

import com.Envent.TecpostgreAqAws.domain.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    // Este método permite buscar eventos com paginação (exigência do desafio)
    @Query("SELECT e FROM Event e WHERE (:title IS NULL OR e.title LIKE %:title%) AND (:date IS NULL OR e.date >= :date)")
    Page<Event> findByFilters(@Param("title") String title, @Param("date") Date date, Pageable pageable);
}