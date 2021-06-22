package com.socgen.stockmarketcharting.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private String username;
    private String password;
    private String role;
    private String email;
    private String mobileNumber;
    private Boolean isVerified;

    public UserEntity(String username, String password, String role, String email, String mobileNumber, Boolean isVerified) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.isVerified = isVerified;
    }
}
