package com.hafidtech.gunungcondongcom.repository;

import com.hafidtech.gunungcondongcom.exception.ResourceNotFoundException;
import com.hafidtech.gunungcondongcom.model.user.User;
import com.hafidtech.gunungcondongcom.security.UserPrincipal;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(@NotBlank String username);
    Optional<User> findByEmail(@NotBlank String email);

    Boolean existsByUsername(@NotBlank String username);

    Boolean existsByEmail(@NotBlank String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    default User getUser(UserPrincipal currentUser) {
        return getUserByName(currentUser.getUsername());
    }

    default User getUserByName(String username) {
        return findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }
    @Query(value = "delete from user_role where user_id = ?1",
            nativeQuery = true)
    Long deleteUserRoleByUserId(Long userId);
}
