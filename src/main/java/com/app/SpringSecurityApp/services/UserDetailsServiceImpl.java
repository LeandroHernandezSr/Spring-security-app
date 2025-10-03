package com.app.SpringSecurityApp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.SpringSecurityApp.entities.UserEntity;
import com.app.SpringSecurityApp.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    private final UserRepository userRepository;
    

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity=userRepository.findUserEntityByUsername(username)
            .orElseThrow(()->new UsernameNotFoundException("El usuario no existe"));

        List<SimpleGrantedAuthority> authorities=new ArrayList<>();

        userEntity.getRol()
        .forEach(
            rol->authorities.add(new SimpleGrantedAuthority("ROLE_".concat(rol.getRoleEnum().name())))
        );

        userEntity.getRol()
        .stream().
            flatMap(rol->
                rol.getPermissionEntities().stream()
            )
            .forEach(
                p->authorities.add(new SimpleGrantedAuthority(p.getName()))
            );

        return new User(userEntity.getUsername(),
            userEntity.getPassword(),
            userEntity.isEnabled(),
            userEntity.isAccountNoExpired(),
            userEntity.isCredentialNoExpired(),
            userEntity.isAccountNoLocked(),
            authorities
        );
    }

}
