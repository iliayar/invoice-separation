package io.swagger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ru.itmo.invoiceseparation.model.ApiTokenRepository;
import ru.itmo.invoiceseparation.model.UserRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "io.swagger", "ru.itmo.invoiceseparation.api" , "ru.itmo.invoiceseparation.model" , "io.swagger.configuration"})
@EnableJpaRepositories(basePackages = {"ru.itmo.invoiceseparation.model"})
@EntityScan("ru.itmo.invoiceseparation.model")
public class Swagger2SpringBoot implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }

    @Bean
    public CommandLineRunner deleteUsers(UserRepository repository) {
        return args -> {
            repository.deleteAll();
        };
    }

    @Bean
    public CommandLineRunner deleteTokens(ApiTokenRepository repository) {
        return args -> {
            repository.deleteAll();
        };
    }
}
