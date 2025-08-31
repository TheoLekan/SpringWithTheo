package com.springwiththeo.week12.method_security.model;

import com.springwiththeo.week12.method_security.repository.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
   private final Long id;
   private final String username;
   private final String password;
   private final Set<GrantedAuthority> authorities;

   public CustomUserDetails(User user) {
      this.id= user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        authorities=user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }



    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
