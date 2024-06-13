package com.hafidtech.gunungcondongcom.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InfoRequest {

    @NotBlank
    private String street;

    @NotBlank
    private String suite;

    @NotBlank
    private String city;

    @NotBlank
    private String zipcode;

    private String catchPhrase;

    private String bs;

    private String phone;

    private String lat;

    private String lng;
}
