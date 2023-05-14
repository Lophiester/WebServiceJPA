package com.lophiester.webService.config;

import com.lophiester.webService.entities.User;
import com.lophiester.webService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "2133213", "988888888");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "121212", "977777777");
        User u3 = new User(null, "Luan Nav", "luan@gmail.com", "121212", "2131231");

        userRepository.saveAll(Arrays.asList(u1, u2,u3));

    }
}
