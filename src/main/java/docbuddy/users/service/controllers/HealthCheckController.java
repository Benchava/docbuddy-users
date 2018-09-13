package docbuddy.users.service.controllers;

import docbuddy.users.service.responses.HealthCheckResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @RequestMapping("/healthcheck")
    public HealthCheckResponse healthCheck(@RequestParam(value = "name", defaultValue = "Dr.") String name) {
        return new HealthCheckResponse(String.format("Service is up %s!!", name));
    }
}
