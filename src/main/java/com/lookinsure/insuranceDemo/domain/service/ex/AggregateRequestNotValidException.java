package com.lookinsure.insuranceDemo.domain.service.ex;

import com.lookinsure.insuranceDemo.domain.AbstractBusinessDomainException;

public class AggregateRequestNotValidException extends AbstractBusinessDomainException {
    public AggregateRequestNotValidException(String code, Object... args) {
        super(code, args);
    }
}
