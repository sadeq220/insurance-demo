package com.lookinsure.insuranceDemo.domain.port.outbound;

import com.lookinsure.insuranceDemo.domain.model.InsuranceProviderDomain;

import java.util.Optional;

public interface InsuranceProviderRepository {
    Optional<InsuranceProviderDomain> find(Long id);
}
