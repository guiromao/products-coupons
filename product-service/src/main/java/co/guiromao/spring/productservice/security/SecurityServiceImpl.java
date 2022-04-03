package co.guiromao.spring.productservice.security;

import co.guiromao.spring.productservice.config.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private UserDetailsServiceImpl userDetailsService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public SecurityServiceImpl(UserDetailsServiceImpl userDetailsService,
                               AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public boolean login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(token);
        boolean isLoggedIn = token.isAuthenticated();

        if (isLoggedIn) {
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        return isLoggedIn;
    }

}
