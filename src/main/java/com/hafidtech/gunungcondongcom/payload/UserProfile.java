package com.hafidtech.gunungcondongcom.payload;

import com.hafidtech.gunungcondongcom.model.user.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Instant joinedAt;
    private String email;
    private Address address;
    private String phone;
}
