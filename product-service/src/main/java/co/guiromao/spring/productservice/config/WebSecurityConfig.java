package co.guiromao.spring.productservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.List;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/v1/products-api/products/**",
                        "/products", "/index", "/showGetProduct",
                        "/productDetails").hasAnyRole("USER", "ADMIN")
                .mvcMatchers("/getProduct").hasAnyRole("USER", "ADMIN")
                .mvcMatchers(HttpMethod.POST, "/v1/products-api/products").hasRole("ADMIN")
                .mvcMatchers("/showCreateProduct", "/saveProduct").hasRole("ADMIN")
                .mvcMatchers("/login", "/").permitAll()
                .anyRequest().denyAll()
                .and()
                .logout().logoutSuccessUrl("/");

        http.csrf(csrfCustomizer -> {
            RequestMatcher requestMatcher = new RegexRequestMatcher(".*", "POST");
            csrfCustomizer.ignoringRequestMatchers(requestMatcher);
        });

        http.cors(corsCustomizer -> {
            CorsConfigurationSource corsConfigurationSource = request -> {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowedOrigins(List.of("localhost:3000", "localhost:3002"));
                corsConfiguration.setAllowedMethods(List.of("POST", "GET"));

                return corsConfiguration;
            };
            corsCustomizer.configurationSource(corsConfigurationSource);
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
