package com.socgen.stockmarketcharting.payload.request;

import lombok.Data;

@Data
public class SignupRequest {

    private String username;
    private String password;
    private String email;
    private String mobileNumber;
    private String role;
}
