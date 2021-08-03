package com.tui.proof.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tui.proof.annotation.AlphaConstraint;
import com.tui.proof.annotation.PhoneConstraint;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BaseClientRQ {
    @AlphaConstraint(mandatory = true)
    private String firstName;

    @AlphaConstraint(mandatory = true)
    private String lastName;

    @PhoneConstraint
    private String telephone;
}
