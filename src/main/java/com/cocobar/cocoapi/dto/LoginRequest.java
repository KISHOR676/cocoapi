package com.cocobar.cocoapi.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String businessEmail;
    private String password;
}