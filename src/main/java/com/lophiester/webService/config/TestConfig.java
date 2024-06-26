package com.lophiester.webService.config;

import com.lophiester.webService.entities.*;
import com.lophiester.webService.entities.enums.CustomerType;
import com.lophiester.webService.entities.enums.StatusPayment;
import com.lophiester.webService.repositories.*;
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
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AddressRepository  addressRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void run(String... args) throws Exception {

        State s1 = new State(null, "Minas Gerais");
        State s2 = new State(null, "São Paulo");

        City c1 = new City(null, "Uberlandia", s1);
        City c2 = new City(null, "Guarulhos", s2);
        City c3 = new City(null, "Campinas", s2);

        s1.getCities().add(c1);
        s2.getCities().add(c2);
        s2.getCities().add(c3);

        stateRepository.saveAll(Arrays.asList(s1, s2));
        cityRepository.saveAll(Arrays.asList(c1, c2, c3));

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", CustomerType.PESSOAFISICA);
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", CustomerType.PESSOAFISICA);
        User u3 = new User(null, "Luan Nav", "luan@gmail.com", "121212", CustomerType.PESSOAJURIDICA);

        u1.getPhoneNumber().addAll(Arrays.asList("11111111111", "222222"));
        u2.getPhoneNumber().addAll(Arrays.asList("333333333", "99595955"));
        u3.getPhoneNumber().addAll(Arrays.asList("444444444"));


        Address a1 = new Address(null, "Rua Lord", "23", null, "jd Esperanca", "071234-50", u1, c1);
        Address a2 = new Address(null, "Rua das Flores", "235", "apato","jd das flores", "071234-50", u1, c2);

        u1.getAddresses().addAll(Arrays.asList(a1, a2));

        userRepository.saveAll(Arrays.asList(u1, u2, u3));

        addressRepository.saveAll(Arrays.asList(a1, a2));


        Order o1 = new Order(null, Instant.parse("2023-06-02T19:53:07Z"),u1,a1);
        Order o2 = new Order(null, Instant.parse("2023-06-01T13:03:03Z"),u1,a2);

        Payment pay1= new PaymentWithCard(null, StatusPayment.PAYMENT_SUCCESS,o1,6);
        o1.setPayment(pay1);

        Payment pay2= new PaymentWithBoleto(null,StatusPayment.PAYMENT_PENDING,o2,Instant.parse("2023-06-30T19:53:07Z"),null);
        o2.setPayment(pay2);

        u1.getOrders().addAll(Arrays.asList(o1, o2));

        orderRepository.saveAll(Arrays.asList(o1, o2));
        paymentRepository.saveAll(Arrays.asList(pay1, pay2));

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");


        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");
        Category cat4 = new Category(null, "Instruments");
        Category cat5 = new Category(null, "CarsPart");
        Category cat6 = new Category(null, "Gym");
        Category cat7 = new Category(null, "Garden");
        Category cat8 = new Category(null, "Software");
        Category cat9 = new Category(null, "Papers");
        Category cat10 = new Category(null, "Lamps");

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10));


        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat3);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        OrderItem oi1 = new OrderItem(o1, p1,1,p1.getPrice(),00.00);
        OrderItem oi2 = new OrderItem(o1, p3,2, p3.getPrice(),80.00);
        OrderItem oi3 = new OrderItem(o2, p2, 2, p3.getPrice(),100.00);

        o1.getItems().addAll(Arrays.asList(oi1, oi2));
        o2.getItems().addAll(Arrays.asList(oi3));

        p1.getItems().addAll(Arrays.asList(oi1));
        p2.getItems().addAll(Arrays.asList(oi3));
        p3.getItems().addAll(Arrays.asList(oi2));

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));


        orderRepository.saveAll(Arrays.asList(o1, o2));


    }
}
