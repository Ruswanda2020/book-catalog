package com.programmerbeginner.catalog.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;

@Entity
@Table(name = "role")
@Data
public class Role implements GrantedAuthority {


    @Serial
    private static final long serialVersionUID = -215883195581440627L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        return "ROLE_ " +name;
    }
}
