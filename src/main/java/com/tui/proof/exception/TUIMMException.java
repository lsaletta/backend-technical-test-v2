package com.tui.proof.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class TUIMMException extends RuntimeException {

    private static final long serialVersionUID = -4105726199013772984L;
    private String errorDescription;

    public TUIMMException(String errorDescription, Throwable throwable) {
        super(throwable);
        this.errorDescription = errorDescription;
    }

}
