package com.lookinsure.insuranceDemo.domain.model.ex;

import com.lookinsure.insuranceDemo.domain.AbstractBusinessDomainException;

public class QuotePriceNotValidException extends AbstractBusinessDomainException {
    public QuotePriceNotValidException(Object... args) {
        super("QUOTE_PRICE_INVALID", args);
    }
}
