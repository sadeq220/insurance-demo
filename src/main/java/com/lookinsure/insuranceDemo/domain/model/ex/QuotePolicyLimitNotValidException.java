package com.lookinsure.insuranceDemo.domain.model.ex;

import com.lookinsure.insuranceDemo.domain.AbstractBusinessDomainException;

public class QuotePolicyLimitNotValidException extends AbstractBusinessDomainException {
    public QuotePolicyLimitNotValidException(Object... args) {
        super("QUOTE_POLICY_LIMIT_INVALID", args);
    }
}
