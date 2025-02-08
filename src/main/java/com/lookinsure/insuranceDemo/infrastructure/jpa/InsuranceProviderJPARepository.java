package com.lookinsure.insuranceDemo.infrastructure.jpa;

import com.lookinsure.insuranceDemo.domain.model.InsuranceProviderDomain;
import com.lookinsure.insuranceDemo.domain.port.outbound.InsuranceProviderRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InsuranceProviderJPARepository implements InsuranceProviderRepository {

    private final InsuranceProviderJPARepositoryDAO insuranceProviderJPARepositoryDAO;

    public InsuranceProviderJPARepository(InsuranceProviderJPARepositoryDAO insuranceProviderJPARepositoryDAO) {
        this.insuranceProviderJPARepositoryDAO = insuranceProviderJPARepositoryDAO;
    }

    @Override
    public Optional<InsuranceProviderDomain> find(Long id) {
        return insuranceProviderJPARepositoryDAO.findById(id);
    }
}
