package br.com.crescer.social.security.service;

import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.security.enumeration.SocialRoles;
import br.com.crescer.social.service.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    UsuarioRepository repository;
    
    //private static final String CRESCER = "12345";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario magrao = repository.findByEmail(username);
        
        String senha = magrao!=null ? magrao.getSenha() : null;
        
        if (username.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
        }
        return new User(username, senha, SocialRoles.valuesToList());
    }

}
