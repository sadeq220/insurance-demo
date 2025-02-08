package com.lookinsure.insuranceDemo.domain.service.ex;

import com.lookinsure.insuranceDemo.domain.AbstractBusinessDomainException;

public class InsuranceProviderNotFoundException extends AbstractBusinessDomainException {
    public InsuranceProviderNotFoundException(Object... args) {
        super("INSURANCE_PROVIDER_NOT_FOUND", args);
    }
}
