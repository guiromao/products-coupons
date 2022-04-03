package co.guiromao.spring.couponservice.controller;

import co.guiromao.spring.couponservice.model.User;
import co.guiromao.spring.couponservice.repo.RolesRepository;
import co.guiromao.spring.couponservice.repo.UserRepository;
import co.guiromao.spring.couponservice.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

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

    @GetMapping("/showReg")
    public String showRegistrationPage() {
        return "registerUser";
    }

    @PostMapping("/registerUser")
    public String register(User user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Set.of(rolesRepository.findByName("ROLE_USER")));
            userRepository.save(user);
        }

        return "login";
    }

}
