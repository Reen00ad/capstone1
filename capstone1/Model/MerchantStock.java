package com.example.capstone1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotNull(message = "id cannot be null")
    private Integer id;
    @NotNull(message = "product id cannot be null")
    private Integer productid;
    @NotNull(message = "merchant id cannot be null")
    private Integer merchantid;
    @NotNull(message = "stock cannot be empty")
    @Min(value = 10,message = "stock must be more than 10 at start ")
    private Integer stock;
}
