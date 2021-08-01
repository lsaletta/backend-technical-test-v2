package com.tui.proof.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class TUIMMException extends RuntimeException {

    private static final long serialVersionUID = -4105726199013772984L;
    private final String errorDescription;

    /**
     * Custom construct.
     *
     * @param errorDescription
     * @param throwable
     */
    public TUIMMException(final String errorDescription, final Throwable throwable) {
        super(throwable);
        this.errorDescription = errorDescription;
    }

}
