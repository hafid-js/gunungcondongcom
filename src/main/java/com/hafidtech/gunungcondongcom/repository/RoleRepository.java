package com.hafidtech.gunungcondongcom.repository;

import com.hafidtech.gunungcondongcom.model.role.Role;
import com.hafidtech.gunungcondongcom.model.role.RoleName;
import com.hafidtech.gunungcondongcom.payload.ApiResponse;
import com.hafidtech.gunungcondongcom.security.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
}
