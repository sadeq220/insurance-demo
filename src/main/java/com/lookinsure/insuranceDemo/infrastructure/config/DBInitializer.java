package com.lookinsure.insuranceDemo.infrastructure.config;

import com.lookinsure.insuranceDemo.domain.model.InsuranceProviderDomain;
import com.lookinsure.insuranceDemo.infrastructure.jpa.InsuranceProviderJPARepositoryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * seed the database with insurance providers
 */
@Component
public class DBInitializer {
    private final InsuranceProviderJPARepositoryDAO insuranceProviderJPARepositoryDAO;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public DBInitializer(InsuranceProviderJPARepositoryDAO insuranceProviderJPARepositoryDAO) {
        this.insuranceProviderJPARepositoryDAO = insuranceProviderJPARepositoryDAO;
    }

    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void initDB() {
        InsuranceProviderDomain dubaiInsuranceProvider = new InsuranceProviderDomain();
        dubaiInsuranceProvider.setName("Dubai National Insurance");
        InsuranceProviderDomain germanyInsuranceProvider = new InsuranceProviderDomain();
        germanyInsuranceProvider.setName("Germany National Insurance");
        InsuranceProviderDomain thirdPartyInsuranceProvider = new InsuranceProviderDomain();
        thirdPartyInsuranceProvider.setName("Third Party Insurance");
        insuranceProviderJPARepositoryDAO.save(dubaiInsuranceProvider);
        insuranceProviderJPARepositoryDAO.save(germanyInsuranceProvider);
        insuranceProviderJPARepositoryDAO.save(thirdPartyInsuranceProvider);
    }
}
