package co.guiromao.spring.couponservice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String COUPON_RESOURCE_ID = "couponService";

    @Value("${security.privateKey}")
    private String privateKey;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(COUPON_RESOURCE_ID).tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/v1/coupons-api/coupons/**")//{code:^[A-Z]*$}")
                        .hasAnyRole("USER", "ADMIN")
                .mvcMatchers(HttpMethod.POST, "/v1/coupons-api/coupons").hasRole("ADMIN")
                .anyRequest().denyAll();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //        KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(new ClassPathResource(keyFile), password.toCharArray());
        //        KeyPair keyPair = keyFactory.getKeyPair(alias);
        //        jwtAccessTokenConverter.setKeyPair(keyPair);
        jwtAccessTokenConverter.setSigningKey(privateKey);

        return jwtAccessTokenConverter;
    }

}
