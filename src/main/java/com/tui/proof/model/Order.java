package com.tui.proof.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Order {
    private Long number;
    private Address deliveryAddress;
    private int pilotes;
    private double orderTotal;
    private Client clientInformation;
}
