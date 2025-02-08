package com.lookinsure.insuranceDemo.domain.service.ex;

import com.lookinsure.insuranceDemo.domain.AbstractBusinessDomainException;

public class AddQuoteRequestNotValidException extends AbstractBusinessDomainException {
    public AddQuoteRequestNotValidException(String code, Object... args) {
        super(code, args);
    }
}
