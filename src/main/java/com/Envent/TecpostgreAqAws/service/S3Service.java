package com.Envent.TecpostgreAqAws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private S3Client s3Client; // Aqui o Spring injeta o Bean que configuramos!

    public String uploadImg(MultipartFile multipartFile) {
        String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .contentType(multipartFile.getContentType())
                    .build();

            // Envia o arquivo diretamente do fluxo de dados (sem precisar criar arquivo temporário no seu PC)
            s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(multipartFile.getInputStream(), multipartFile.getSize()));

            // Retorna a URL da imagem (Ajuste para sua região se necessário)
            return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, "sa-east-1", fileName);

        }  catch (Exception e) { // Mude de IOException para Exception
        System.out.println("ERRO CRÍTICO NO S3: " + e.getMessage());
        e.printStackTrace();
        return null;
        }
    }
}