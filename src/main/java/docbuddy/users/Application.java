package docbuddy.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"docbuddy.users.service", "docbuddy.users.persistence.dao", "docbuddy.users.service.controllers", "docbuddy.users.persistence"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
