package com.mati.vaii.service;

import com.mati.vaii.dto.UserDTO;
import com.mati.vaii.model.Users;
import com.mati.vaii.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(UserDTO user) {
        Users newUser = new Users();

//		newUser.setFullname(user.fullname);
        //newUser.setId(user.id);
        newUser.setPassword(bCryptPasswordEncoder.encode(user.password));
        newUser.setEmail(user.email.toLowerCase());
        newUser.setRole(user.role);

        userRepository.save(newUser);
    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Users user = userRepository.findByEmail(email);
        if (user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRole().toString());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }


    private List<GrantedAuthority> getUserAuthority(String userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRoles));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }


    private UserDetails buildUserForAuthentication(Users user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    }
