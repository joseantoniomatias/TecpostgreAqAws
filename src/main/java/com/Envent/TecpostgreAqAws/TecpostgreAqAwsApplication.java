package com.Envent.TecpostgreAqAws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {io.awspring.cloud.autoconfigure.s3.S3AutoConfiguration.class})
public class TecpostgreAqAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TecpostgreAqAwsApplication.class, args);

	}

}
