package io.chege.praco_api.auth;

import io.chege.praco_api.user.User;
import io.chege.praco_api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public AuthUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getUserByEmail(s);
        if (user.isPresent() && user.get().getStatus()){
            return new AuthUser(user.get().getEmail(),user.get().getPassword(), null, true, true, true, true);
        }else{
            throw new UsernameNotFoundException("Username not found");
        }
    }
}
