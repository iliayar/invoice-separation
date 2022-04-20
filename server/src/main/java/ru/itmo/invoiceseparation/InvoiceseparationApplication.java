package ru.itmo.invoiceseparation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ru.itmo.invoiceseparation.entities.*;

@EnableSwagger2
@SpringBootApplication
public class InvoiceseparationApplication {

    private static final Logger log = LoggerFactory.getLogger(InvoiceseparationApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(InvoiceseparationApplication.class, args);
    }

    // @Bean
    // public CommandLineRunner demo(UserRepository repository) {
    //     return args -> {
    //         repository.save(new User("AMOGUS"));
    //         for (User user : repository.findAll()) {
    //             log.info(user.toString());
    //         }
    //     };
    // }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return args -> {
            repository.deleteAll();
        };
    }
}
