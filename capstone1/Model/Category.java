package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @NotNull(message = "id cannot be null")
    private Integer id;
    @NotEmpty(message = "name cannot be empty")
    @Size(min = 3,message = "name must be more than 3 character")
    private String name;
}
