package com.github.dmtk.web;

import com.github.dmtk.dao.UserDAO;
import com.github.dmtk.entity.Group;
import com.github.dmtk.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserDAO userDAO;
    
    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        com.github.dmtk.entity.User user = userDAO.getByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("user with email " + email + " not found");
        UserDetails userDetails = buildUserFromEntity(user);
        
        return userDetails;
    }

    private User buildUserFromEntity(com.github.dmtk.entity.User entity) {
        String username = entity.getEmail();
        String password = entity.getPasswordHash();
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        addAllEntityUserAuthorities(authorities, entity);
        return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    private void addAllEntityUserAuthorities(List<GrantedAuthority> authorities, com.github.dmtk.entity.User entity) {
        for (Role role : entity.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }
        for (Group group : entity.getGroups()) {
            for (Role role : group.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getRolename()));
            }
        }
    }
}