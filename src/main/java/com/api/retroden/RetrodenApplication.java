package com.api.retroden;

import com.api.retroden.model.*;
import com.api.retroden.repository.ProfessionelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@SpringBootApplication
public class RetrodenApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetrodenApplication.class, args);
    }

}
