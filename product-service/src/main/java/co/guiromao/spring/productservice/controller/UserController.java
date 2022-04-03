package co.guiromao.spring.productservice.controller;

import co.guiromao.spring.productservice.repo.RolesRepository;
import co.guiromao.spring.productservice.repo.UserRepository;
import co.guiromao.spring.productservice.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private SecurityService securityService;
    private UserRepository userRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(SecurityService securityService,
                          UserRepository userRepository,
                          RolesRepository rolesRepository,
                          PasswordEncoder passwordEncoder){
        this.securityService = securityService;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("email") String email,
                          @RequestParam("password") String password) {
        return securityService.login(email, password) ?
                "index"
                : "login";
    }

}
