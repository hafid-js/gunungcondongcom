package com.hafidtech.gunungcondongcom.services;

import com.hafidtech.gunungcondongcom.payload.UserIdentityAvailability;
import com.hafidtech.gunungcondongcom.payload.UserSummary;
import com.hafidtech.gunungcondongcom.security.UserPrincipal;

public interface UserService {

    UserSummary getCurrentUser(UserPrincipal currentUser);

    UserIdentityAvailability checkUsernameAvailability(String username);

    UserIdentityAvailability checkEmailAvailability(String email);

    UserPro
}
