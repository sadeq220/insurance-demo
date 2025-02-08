package com.lookinsure.insuranceDemo.domain.model.ex;

import com.lookinsure.insuranceDemo.domain.AbstractBusinessDomainException;

public class QuoteCoverageNotValidException extends AbstractBusinessDomainException {
    public QuoteCoverageNotValidException(Object... args) {
        super("QUOTE_COVERAGE_INVALID", args);
    }
}
