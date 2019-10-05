package by.pvt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAuthenticationManager implements AuthenticationManager {

    static MyAuthenticationManager manager = new MyAuthenticationManager();

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Object principal = authentication.getPrincipal();
        if (principal == null) throw new DisabledException("Account is null");

        Object credentials = authentication.getCredentials();
        if (credentials == null) throw new BadCredentialsException("Credentials is null");

        Authentication response = null;
        if (principal.equals(credentials)) {
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER_ROLE");
            List<SimpleGrantedAuthority> list = new ArrayList<>();
            list.add(grantedAuthority);
            response = new UsernamePasswordAuthenticationToken(
                    authentication.getPrincipal(),
                    authentication.getCredentials(),
                    list
            );
        } else {
            throw new BadCredentialsException("Credentials is not valid");
        }

        return response;
    }

    public static void main(String[] args) {

        String username = "test";
        String password = "test";
        Authentication request = new UsernamePasswordAuthenticationToken(username, password);

        Authentication response = manager.authenticate(request);

        final Collection<? extends GrantedAuthority> authorities = response.getAuthorities();
        System.out.println("Details: " + response.getDetails());
        System.out.println("isAuthenticated= " + response.isAuthenticated());
        System.out.println(authorities);
    }
}
