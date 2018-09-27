package docbuddy.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"docbuddy.users.service", "docbuddy.users.persistence.dao", "docbuddy.users.service.controllers", "docbuddy.users.persistence", "docbuddy.users.config", "docbuddy.users.util"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
