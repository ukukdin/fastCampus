package springSecurity.facc_01.config;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerAuthenticationProvider implements AuthenticationProvider {
    //인증 논리를 추가하는 메소드

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        if("user".equals(username) && "password".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, List.of());
        }
       throw new RuntimeException("auth Error");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
