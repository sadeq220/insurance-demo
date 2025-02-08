package com.lookinsure.insuranceDemo.domain.service;

import com.lookinsure.insuranceDemo.domain.model.InsuranceProviderDomain;
import com.lookinsure.insuranceDemo.domain.model.QuoteDomain;
import com.lookinsure.insuranceDemo.domain.port.outbound.InsuranceProviderRepository;
import com.lookinsure.insuranceDemo.domain.port.value.*;
import com.lookinsure.insuranceDemo.domain.port.inbound.QuotePort;
import com.lookinsure.insuranceDemo.domain.port.outbound.QuoteRepository;
import com.lookinsure.insuranceDemo.domain.service.ex.AggregateRequestNotValidException;
import com.lookinsure.insuranceDemo.domain.service.ex.InsuranceProviderNotFoundException;
import com.lookinsure.insuranceDemo.domain.service.ex.QuoteNotFoundException;
import com.lookinsure.insuranceDemo.domain.service.mapper.QuoteDomainMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteService implements QuotePort {
    private final QuoteRepository quoteRepository;
    private final QuoteDomainMapper quoteDomainMapper;
    private final InsuranceProviderRepository insuranceProviderRepository;

    public QuoteService(QuoteRepository quoteRepository,
                        QuoteDomainMapper quoteDomainMapper,
                        InsuranceProviderRepository insuranceProviderRepository) {
        this.quoteRepository = quoteRepository;
        this.quoteDomainMapper = quoteDomainMapper;
        this.insuranceProviderRepository = insuranceProviderRepository;
    }

    @Transactional
    @Override
    public AggregateResponseValue aggregate(AggregateRequestValue aggregateRequestValue) {
        AggregateRequestValue reformedAggregateRequest = validateAggregateRequest(aggregateRequestValue);
        QuoteAggregateRepositoryResponseValue aggregateResponse = quoteRepository.aggregate(reformedAggregateRequest);
        List<QuoteValue> quoteValues = aggregateResponse.quoteDomains().stream().map(quoteDomain -> quoteDomainMapper.toValue(quoteDomain)).toList();
        return new AggregateResponseValue(quoteValues,
                aggregateResponse.size(),
                aggregateResponse.page(),
                aggregateResponse.sortedBy());
    }

    @Transactional
    @Override
    public QuoteValue addQuote(AddQuoteValue addQuoteValue) {
        QuoteDomain quoteDomain = QuoteDomain.create(addQuoteValue);
        Optional<InsuranceProviderDomain> insuranceProviderDomain = insuranceProviderRepository.find(addQuoteValue.providerId());
        insuranceProviderDomain.orElseThrow(InsuranceProviderNotFoundException::new);
        insuranceProviderDomain.get().addQuote(quoteDomain);
        QuoteDomain persistedQuote = quoteRepository.saveQuote(quoteDomain);// to populate generated Id
        return quoteDomainMapper.toValue(persistedQuote);
    }

    @Transactional(readOnly = true)
    @Override
    public QuoteValue getQuote(Long id) {
        Optional<QuoteDomain> optionalQuoteDomain = quoteRepository.getQuote(id);
        optionalQuoteDomain.orElseThrow(QuoteNotFoundException::new);
        return quoteDomainMapper.toValue(optionalQuoteDomain.get());
    }

    @Transactional
    @Override
    public QuoteValue removeQuote(Long id) {
        Optional<QuoteDomain> optionalQuoteDomain = quoteRepository.getQuote(id);
        optionalQuoteDomain.orElseThrow(QuoteNotFoundException::new);
        QuoteDomain quoteDomain = optionalQuoteDomain.get();
        quoteRepository.removeQuote(quoteDomain);
        return quoteDomainMapper.toValue(quoteDomain);
    }

    @Transactional
    @Override
    public QuoteValue updateQuote(Long id, UpdateQuoteValue updateQuoteValue) {
        Optional<QuoteDomain> optionalQuoteDomain = quoteRepository.getQuote(id);
        optionalQuoteDomain.orElseThrow(QuoteNotFoundException::new);
        QuoteDomain quoteDomain = optionalQuoteDomain.get();
        quoteDomain.update(updateQuoteValue);
        return quoteDomainMapper.toValue(quoteDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public List<QuoteValue> listAllQuotes() {
        List<QuoteDomain> quoteDomains = quoteRepository.listAllQuotes();
        List<QuoteValue> quoteValues = quoteDomains.stream().map(quoteDomainMapper::toValue).toList();
        return quoteValues;
    }

    /**
     * check business rules
     */
    private AggregateRequestValue validateAggregateRequest(AggregateRequestValue aggregateRequestValue) {
        if (aggregateRequestValue.size() < 1) {
            throw new AggregateRequestNotValidException("QUOTE_AGGREGATE_SIZE_INVALID");
        }
        if (aggregateRequestValue.page() < 0) {
            throw new AggregateRequestNotValidException("QUOTE_AGGREGATE_PAGE_INVALID");
        }
        try {
            SortBy sortBy = SortBy.valueOf(aggregateRequestValue.sortBy().toUpperCase());
            return new AggregateRequestValue(sortBy.getFieldName(),
                    aggregateRequestValue.size(),
                    aggregateRequestValue.page());
        } catch (IllegalArgumentException ex) {
            throw new AggregateRequestNotValidException("QUOTE_AGGREGATE_SORT_INVALID");
        }
    }

    /**
     * accepted sort params
     */
    private enum SortBy{
        PRICE("price"),POLICYLIMIT("policyLimit");
        private final String fieldName;
        SortBy(String fieldName) {
            this.fieldName=fieldName;
        }

        public String getFieldName() {
            return fieldName;
        }
    }
}
