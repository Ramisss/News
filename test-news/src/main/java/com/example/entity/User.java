package com.example.entity;

import com.example.entity.templete.AbsEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User extends AbsEntity implements UserDetails {

    private String firstName;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private String password;

    private String age;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @ToString.Exclude
    private Set<Role> roles;

    private boolean enabled=true;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }



    public User(String firstName, String userName, String email, String password, Set<Role> roles, boolean enabled, String age) {
        this.firstName = firstName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.enabled = enabled;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
