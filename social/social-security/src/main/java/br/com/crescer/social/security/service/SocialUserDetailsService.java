package br.com.crescer.social.security.service;

import br.com.crescer.social.security.enumeration.SocialRoles;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Carlos H. Nonnemacher
 */
@Service
public class SocialUserDetailsService implements UserDetailsService {

    private static final String CRESCER = "crescer";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
        }
        return new User(username, new BCryptPasswordEncoder().encode(CRESCER), SocialRoles.valuesToList());
    }

}
