package com.tui.proof.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tui.proof.annotation.PilotesConstraint;
import com.tui.proof.model.Address;
import lombok.Data;

import javax.validation.Valid;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BaseOrderRQ {

    @Valid
    private Address deliveryAddress;

    @PilotesConstraint
    private int pilotes;
}
