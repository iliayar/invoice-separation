package ru.itmo.invoiceseparation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.sql.*;

@EnableSwagger2
@SpringBootApplication
public class InvoiceseparationApplication {

    public static void main(String[] args) {
        // TODO: remove, checking db
        Connection db = null;
        try {
            String user = System.getenv("SQL_USER");
            String password = System.getenv("SQL_PASSWORD");
            String host = System.getenv("SQL_HOST");
            String port = System.getenv("SQL_PORT");
            String db_name = System.getenv("SQL_DB");

            String url = "jdbc:postgresql://" + host + ":" + port + "/" + db_name;
            System.out.println("Connecting to postgres: " + url);
            db = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to PostgreSLQ server successfully");
            db.close();
        } catch (SQLException e) {
            System.out.println("Failed to connect to PostgreSQL: " + e.getMessage());
        }

        SpringApplication.run(InvoiceseparationApplication.class, args);
    }

}
