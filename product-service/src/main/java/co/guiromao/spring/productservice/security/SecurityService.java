package co.guiromao.spring.productservice.security;

public interface SecurityService {

    boolean login(String email, String password);

}
