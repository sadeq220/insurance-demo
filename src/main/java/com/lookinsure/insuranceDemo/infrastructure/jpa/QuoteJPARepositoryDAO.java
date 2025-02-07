package com.lookinsure.insuranceDemo.infrastructure.jpa;

import com.lookinsure.insuranceDemo.domain.model.QuoteDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteJPARepositoryDAO extends JpaRepository<QuoteDomain,Long>, JpaSpecificationExecutor<QuoteDomain> {
    @Query("SELECT qu FROM quote qu JOIN FETCH qu.insuranceProvider") // to avoid N+1 query
    Page<QuoteDomain> aggregateQuotes(Pageable pageable);
}
