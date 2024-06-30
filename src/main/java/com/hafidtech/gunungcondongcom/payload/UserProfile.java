package com.hafidtech.gunungcondongcom.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hafidtech.gunungcondongcom.model.user.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Long joinedAt;
    private String email;
    private Address address;
    private String phone;
}
