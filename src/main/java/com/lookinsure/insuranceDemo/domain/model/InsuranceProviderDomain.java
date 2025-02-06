package com.lookinsure.insuranceDemo.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "provider")
@Table(name = "PROVIDER")
@Getter
@Setter
public class InsuranceProviderDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "insuranceProvider",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<QuoteDomain> quoteList = new ArrayList<>();

    public List<QuoteDomain> getQuoteList() {
        return List.copyOf(quoteList);//enforcing design principles, quote list modifications should only be done by provided methods
    }

    public void addQuote(QuoteDomain quoteDomain){
        quoteDomain.setInsuranceProvider(this);
        quoteList.add(quoteDomain);
    }
    public void removeQuote(QuoteDomain quoteDomain){
        quoteDomain.setInsuranceProvider(null);
        quoteList.remove(quoteDomain);
    }
}
