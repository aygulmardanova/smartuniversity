§package ru.kpfu.itis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kpfu.itis.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.security.core.userdetails.User;

/**
 * Created by aygulmardanova on 27.04.16.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        UserEntity user = userService.getUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User with login " + login + " not found");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        UserDetails details = new org.springframework.security.core.userdetails.User
                (user.getLogin(), user.getPassword(), authorities);

        return details;
    }

}