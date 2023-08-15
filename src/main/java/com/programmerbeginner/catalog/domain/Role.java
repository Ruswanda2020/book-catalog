package com.programmerbeginner.catalog.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;

@Entity
@Table(name = "roles")
@Data
public class Role implements GrantedAuthority {

    @Serial
    private static final long serialVersionUID = 2950913889326669618L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(name = "names",nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        return "Role_"+name;
    }
}
