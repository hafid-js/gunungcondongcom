package com.hafidtech.gunungcondongcom.controller;

import com.hafidtech.gunungcondongcom.model.user.User;
import com.hafidtech.gunungcondongcom.payload.*;
import com.hafidtech.gunungcondongcom.security.CurrentUser;
import com.hafidtech.gunungcondongcom.security.UserPrincipal;
import com.hafidtech.gunungcondongcom.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserSummary> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = userService.getCurrentUser(currentUser);

        return new ResponseEntity<>(userSummary, HttpStatus.OK);
    }

    @GetMapping("/checkUsernameAvailability")
    public ResponseEntity<UserIdentityAvailability> checkUsernameAvailability(@RequestParam(value = "username") String username) {
        UserIdentityAvailability userIdentityAvailability = userService.checkUsernameAvailability(username);
        return new ResponseEntity<>(userIdentityAvailability, HttpStatus.OK);
    }

    @GetMapping("/checkEmailAvailability")
    public ResponseEntity<UserIdentityAvailability> checkEmailAvailability(@RequestParam(value = "email") String email) {
        UserIdentityAvailability userIdentityAvailability = userService.checkEmailAvailability(email);
        return new ResponseEntity< >(userIdentityAvailability, HttpStatus.OK);
    }

    @GetMapping("/{username}/profile")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable(value = "username") String username) {
        UserProfile userProfile = userService.getUserProfile(username);

        return new ResponseEntity< >(userProfile, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User newUser = userService.addUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User newUser,
                                           @PathVariable(value = "username") String username,
                                           @CurrentUser UserPrincipal currentUser) {
        User updateUser = userService.updateUser(newUser, username, currentUser);

        return new ResponseEntity<>(updateUser, HttpStatus.CREATED);

    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "username") String username, @CurrentUser UserPrincipal currentUser) {
        ApiResponse apiResponse = userService.deleteUser(username, currentUser);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{username}/giveadmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> giveAdmin(@PathVariable(value = "username") String username) {
        ApiResponse apiResponse = userService.giveAdmin(username);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{username}/takeadmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> takeAdmin(@PathVariable(value = "username") String username) {
        ApiResponse apiResponse = userService.removeAdmin(username);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/setOrUpdateInfo")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserProfile> setAddress(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody InfoRequest infoRequest) {
        UserProfile userProfile = userService.setOrUpdateInfo(currentUser, infoRequest);

        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

}
