package com.mercheazy.auth_service.model;

import com.mercheazy.auth_service.dto.AuthUserResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "auth_user")
public class AuthUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "au_id")
    private Long id;

    @Column(name = "au_username", unique = true)
    private String username;

    @Column(name = "au_password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "au_role", nullable = false)
    private Role role;

    @Column(name = "au_enabled")
    private Boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public enum Role {
        USER, ADMIN
    }

    public AuthUserResponseDto toResponseDto() {
        AuthUserResponseDto responseDto = new AuthUserResponseDto();
        responseDto.setUsername(this.username);
        responseDto.setRole(this.role.name());
        return responseDto;
    }
}
