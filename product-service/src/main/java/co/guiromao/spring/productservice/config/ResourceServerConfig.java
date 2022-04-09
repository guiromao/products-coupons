package co.guiromao.spring.productservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String PRODUCT_RESOURCE_ID = "productService";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(PRODUCT_RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/v1/products-api/products/**")//{code:^[A-Z]*$}")
                .hasAnyRole("USER", "ADMIN")
                .mvcMatchers(HttpMethod.POST, "/v1/products-api/products").hasRole("ADMIN")
                .anyRequest().denyAll();
    }

}
