package vn.edu.hust.project3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.edu.hust.project3.model.Role;
import vn.edu.hust.project3.model.User;
import vn.edu.hust.project3.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service("UserDetailServiceImpl")
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findAccountByUsername(username);
        if (optional == null) {
            System.out.println("User not found !" + username);
            throw new UsernameNotFoundException("User " + username + "was not found in the database.");
        }
        System.out.println("Found user!" + username);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(optional.get().getUsername(),
                optional.get().getPassword(), getRole(optional));
        System.out.println(userDetails);
        return userDetails;
    }

    public Set<SimpleGrantedAuthority> getRole(Optional<User> optional) {
        Role role = optional.get().getRole();
        return Collections.singleton(new SimpleGrantedAuthority(role.getName()));
    }

}
