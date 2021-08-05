package com.tui.proof.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EStatusType {
    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE");

    private String value;

}
