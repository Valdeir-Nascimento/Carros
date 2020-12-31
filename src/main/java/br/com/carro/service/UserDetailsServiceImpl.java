package br.com.carro.service;

import br.com.carro.domain.Usuario;
import br.com.carro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
        Usuario usuario = userRepository.findByLogin(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.withUsername(username).password(usuario.getSenha()).roles("USER").build();
    }
}
