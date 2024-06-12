package com.hafidtech.gunungcondongcom.repository;

import com.hafidtech.gunungcondongcom.model.user.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(@NotBlank String username);
    Optional<User> findByEmail(@NotBlank String email);

    Boolean existingByUsername(@NotBlank String username);

    Boolean existingByEmail(@NotBlank String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    default User getUser(UserPrinci)
}
