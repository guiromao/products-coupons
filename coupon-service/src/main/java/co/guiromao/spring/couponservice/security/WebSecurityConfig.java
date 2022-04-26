package co.guiromao.spring.couponservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
        http.httpBasic();
//        http
//                .authorizeRequests()
//                .mvcMatchers(HttpMethod.GET, "/v1/coupons-api/coupons/{code:^[A-Z]*$}",
//                        "/index", "/showGetCoupon", "/getCoupon", "/couponDetails")
//                //.hasAnyRole("USER", "ADMIN")
//                .permitAll()
//                .mvcMatchers(HttpMethod.GET, "/createCoupon", "/showCreateCoupon",
//                        "/createResponse").hasRole("ADMIN")
//                .mvcMatchers(HttpMethod.POST, "/getCoupon").hasAnyRole("USER", "ADMIN")
//                .mvcMatchers(HttpMethod.POST, "/v1/coupons-api/coupons",
//                        "/saveCoupon").hasRole("ADMIN")
//                .mvcMatchers("/", "/login", "/logout", "/showReg", "/registerUser",
//                        "/register").permitAll()
//                .anyRequest().denyAll()
//                .and()
//                .logout().logoutSuccessUrl("/").invalidateHttpSession(false);
//
//        http.cors(corsCustomizer -> {
//            CorsConfigurationSource corsConfigurationSource = request -> {
//                CorsConfiguration corsConfiguration = new CorsConfiguration();
//                corsConfiguration.setAllowedOrigins(List.of("localhost:3000", "localhost:3002"));
//                corsConfiguration.setAllowedMethods(List.of("GET"));
//
//                return corsConfiguration;
//            };
//            corsCustomizer.configurationSource(corsConfigurationSource);
//        });
    }

    @Bean
    public PasswordEncoder generatePasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
