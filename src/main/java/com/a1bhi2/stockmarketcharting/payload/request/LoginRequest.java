package com.a1bhi2.stockmarketcharting.payload.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
