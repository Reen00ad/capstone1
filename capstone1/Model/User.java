package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotNull(message = "id cannot be null")
    private Integer id;
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 5,message = "username must be more than 5 character")
    private String username;
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 6,message = "password must be more than 6 character")
   // @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])$")
    private String password;
    @Email
    private String email;
    @NotEmpty(message = "role should not be null")
    @Pattern(regexp ="^(Admin|Customer)$", message = "role must be Admin or Customer only")
    private String role;
    @NotNull(message = "balance cannot be null")
    @Positive
    private double balance;
}
