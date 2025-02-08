package com.lookinsure.insuranceDemo.infrastructure.jpa;

import com.lookinsure.insuranceDemo.domain.model.QuoteDomain;
import com.lookinsure.insuranceDemo.domain.port.value.AggregateRequestValue;
import com.lookinsure.insuranceDemo.domain.port.outbound.QuoteRepository;
import com.lookinsure.insuranceDemo.domain.port.value.QuoteAggregateRepositoryResponseValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
        Page<QuoteDomain> sortedQuotes = quoteJPARepositoryDAO.aggregateQuotes(pageRequest);
        return new QuoteAggregateRepositoryResponseValue(sortedQuotes.getContent(),
                sortedQuotes.getSize(),
                sortedQuotes.getNumber(),
                aggregateRequestValue.sortBy(),
                sortedQuotes.getTotalPages(),
                sortedQuotes.getTotalElements());
    }

    @Override
    public QuoteDomain saveQuote(QuoteDomain quoteDomain) {
        return quoteJPARepositoryDAO.save(quoteDomain);
    }

    @Override
    public Optional<QuoteDomain> getQuote(Long id) {
        return quoteJPARepositoryDAO.getQuoteDomain(id);
    }

    @Override
    public void removeQuote(QuoteDomain quoteDomain) {
        quoteJPARepositoryDAO.delete(quoteDomain);
    }

    @Override
    public List<QuoteDomain> listAllQuotes() {
        return quoteJPARepositoryDAO.findAll();
    }
}
