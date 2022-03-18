package com.example.examfinal.service.impl;

import com.example.examfinal.model.entity.UserEntity;
import com.example.examfinal.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProjectUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public ProjectUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity =
                userRepository.findByUsername(username).
                        orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));

        return mapToUserDetails(userEntity);
    }

    private static UserDetails mapToUserDetails(UserEntity userEntity) {

        List<GrantedAuthority> auhtorities =
                userEntity.
                        getRoles().
                        stream().
                        map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).
                        collect(Collectors.toList());

        return new ProjectUser(
                userEntity.getUsername(),
                userEntity.getPassword(),
                auhtorities
        );
    }
}

