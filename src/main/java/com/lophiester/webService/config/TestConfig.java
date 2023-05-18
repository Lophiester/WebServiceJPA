package com.lophiester.webService.config;

import com.lophiester.webService.entities.Category;
import com.lophiester.webService.entities.Order;
import com.lophiester.webService.entities.Product;
import com.lophiester.webService.entities.User;
import com.lophiester.webService.enums.OrderStatus;
import com.lophiester.webService.repositories.CategoryRepository;
import com.lophiester.webService.repositories.OrderRepository;
import com.lophiester.webService.repositories.ProductRepository;
import com.lophiester.webService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "2133213", "988888888");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "121212", "977777777");
        User u3 = new User(null, "Luan Nav", "luan@gmail.com", "121212", "2131231");

        userRepository.saveAll(Arrays.asList(u1, u2, u3));

        Order o1 = new Order(null, Instant.parse("2023-06-20T19:53:07Z"), OrderStatus.PAYMENT_SUCCESS,u1);
        Order o2 = new Order(null, Instant.parse("2023-03-03T13:03:03Z"), OrderStatus.PAYMENT_PENDING,u2);
        Order o3 = new Order(null, Instant.parse("2023-03-03T13:03:03Z"), OrderStatus.PAYMENT_FAILED,u1);

        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
    }
}
