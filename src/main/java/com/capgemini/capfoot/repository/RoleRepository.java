package com.capgemini.capfoot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.capgemini.capfoot.entity.ERole;
import com.capgemini.capfoot.entity.Role;

@Repository
@CrossOrigin("*")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
