package com.programmerbeginner.catalog.repository;

import com.programmerbeginner.catalog.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AppUserRepository extends JpaRepository<AppUser,String> {

    Optional<AppUser> findByUsername(String username);
}
