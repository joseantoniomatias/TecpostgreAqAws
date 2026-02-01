package com.Envent.TecpostgreAqAws.dto;

import org.springframework.web.multipart.MultipartFile;

public record EventRequestDTO(
        String title,
        String description,
        Long date,
        String city,
        String state,
        Boolean remote,
        String eventUrl,
        MultipartFile image // Adicionado para o upload que faremos depois
)
{

}











