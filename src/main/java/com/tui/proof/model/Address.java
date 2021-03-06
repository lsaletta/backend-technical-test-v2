package com.tui.proof.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Address {

    @NotNull
    private String street;

    @NotNull
    private String postcode;

    @NotNull
    private String city;

    @NotNull
    private String country;
}
