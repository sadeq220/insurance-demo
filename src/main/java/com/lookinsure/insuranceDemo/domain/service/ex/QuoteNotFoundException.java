package com.lookinsure.insuranceDemo.domain.service.ex;

import com.lookinsure.insuranceDemo.domain.AbstractBusinessDomainException;

public class QuoteNotFoundException extends AbstractBusinessDomainException {
    public QuoteNotFoundException(Object... args) {
        super("QUOTE_NOT_FOUND", args);
    }
}
