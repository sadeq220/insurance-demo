package com.lookinsure.insuranceDemo.infrastructure.jpa;

import com.lookinsure.insuranceDemo.domain.model.QuoteDomain;
import com.lookinsure.insuranceDemo.domain.port.value.AggregateRequestValue;
import com.lookinsure.insuranceDemo.domain.port.outbound.QuoteRepository;
import com.lookinsure.insuranceDemo.domain.port.value.QuoteAggregateRepositoryResponseValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * JPA adapter
 */
@Component
public class QuoteJPARepository implements QuoteRepository {
    private final QuoteJPARepositoryDAO quoteJPARepositoryDAO;

    public QuoteJPARepository(QuoteJPARepositoryDAO quoteJPARepositoryDAO) {
        this.quoteJPARepositoryDAO = quoteJPARepositoryDAO;
    }

    @Override
    public QuoteAggregateRepositoryResponseValue aggregate(AggregateRequestValue aggregateRequestValue) {
        PageRequest pageRequest = PageRequest.of(aggregateRequestValue.page(),
                aggregateRequestValue.size(),
                Sort.by(aggregateRequestValue.sortBy()));
        Page<QuoteDomain> sortedQuotes = quoteJPARepositoryDAO.findAll(pageRequest);
        return new QuoteAggregateRepositoryResponseValue(sortedQuotes.getContent(),
                sortedQuotes.getSize(),
                sortedQuotes.getNumber(),
                aggregateRequestValue.sortBy(),
                sortedQuotes.getTotalPages(),
                sortedQuotes.getTotalElements());
    }
}
