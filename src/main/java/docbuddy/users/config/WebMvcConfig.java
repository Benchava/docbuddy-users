package docbuddy.users.config;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
@Setter
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private RequestInterceptor requestInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor).excludePathPatterns("/auth/login");
    }


}