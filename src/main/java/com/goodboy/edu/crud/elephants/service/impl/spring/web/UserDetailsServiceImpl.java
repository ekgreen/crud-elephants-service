package com.goodboy.edu.crud.elephants.service.impl.spring.web;

import com.goodboy.edu.crud.elephants.service.api.services.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var applicationUser = applicationUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
}