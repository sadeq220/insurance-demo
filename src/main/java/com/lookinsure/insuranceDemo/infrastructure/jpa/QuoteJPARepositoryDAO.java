package com.lookinsure.insuranceDemo.infrastructure.jpa;

import com.lookinsure.insuranceDemo.domain.model.QuoteDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteJPARepositoryDAO extends JpaRepository<QuoteDomain,Long>, JpaSpecificationExecutor<QuoteDomain> {
}
