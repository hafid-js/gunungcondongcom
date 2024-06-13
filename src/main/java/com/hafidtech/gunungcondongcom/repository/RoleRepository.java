package com.hafidtech.gunungcondongcom.repository;

import com.hafidtech.gunungcondongcom.model.Role;
import com.hafidtech.gunungcondongcom.model.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
}
