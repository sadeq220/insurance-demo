package com.lookinsure.insuranceDemo.domain;

import lombok.Getter;

@Getter
public abstract class AbstractBusinessDomainException extends RuntimeException{
    private final String code;
    private final Object[] args;

    protected AbstractBusinessDomainException(String code, Object... args) {
        this.code = code;
        this.args = args;
    }
}
