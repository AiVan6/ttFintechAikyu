package com.example.ttwebzaim.services;

import com.example.ttwebzaim.entitys.AccountInfo;
import com.example.ttwebzaim.entitys.LoanRequest;
import com.example.ttwebzaim.entitys.CreditBureau;
import com.example.ttwebzaim.entitys.RequestContent;
import com.example.ttwebzaim.repositories.LoanRequestRepository;
import com.example.ttwebzaim.repositories.RequestContentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RequestContentService {

    private final RequestContentRepository requestContentRepository;

    private final LoanRequestRepository loanRequestRepository;

    private final ObjectMapper objectMapper;
    private final StopFactorService stopFactorCalculator;

    @Autowired
    public RequestContentService(RequestContentRepository requestContentRepository,
                                 LoanRequestRepository loanRequestRepository,
                                 StopFactorService stopFactorCalculator,
                                 ObjectMapper objectMapper) {
        this.requestContentRepository = requestContentRepository;
        this.loanRequestRepository = loanRequestRepository;
        this.stopFactorCalculator = stopFactorCalculator;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void processRequests() {
        List<RequestContent> requestContents = requestContentRepository.findAll();

        for (RequestContent requestContent : requestContents) {
            try {
                LoanRequest loanRequest = objectMapper.readValue(requestContent.getContent(), LoanRequest.class);

                CreditBureau creditBureau = loanRequest.getCreditBureau();
                if (creditBureau != null) {
                    for (AccountInfo accountInfo : creditBureau.getAccountInfos()) {
                        accountInfo.setCreditBureau(creditBureau);
                    }
                }

                loanRequestRepository.save(loanRequest);

                boolean stopFactor = stopFactorCalculator.inputData(loanRequest);
                System.out.println("stop factor: " + stopFactor);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

}
