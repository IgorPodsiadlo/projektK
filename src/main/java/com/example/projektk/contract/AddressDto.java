package com.example.projektk.contract;

import com.example.projektk.model.Address;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AddressDto extends Address {
    private Integer id;
    private String city;
    private String postalCode;
    private String street;
}
