package com.bank.swift_gateway.service;

import com.bank.swift_gateway.model.SwiftPaymentMessage103;
import com.bank.swift_gateway.repository.SwiftPaymentMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SwiftPaymentMessageService {

    @Autowired
    private SwiftPaymentMessageRepository repository;

    public Page<SwiftPaymentMessage103> getMessages(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public void saveMessage(String rawMessage) {
        SwiftPaymentMessage103 message = parseMessage(rawMessage);
        repository.save(message);
    }

    private SwiftPaymentMessage103 parseMessage(String rawMessage) {
        // Implement the parsing logic here
        SwiftPaymentMessage103 message = new SwiftPaymentMessage103();
        // Example parsing logic (this should be replaced with actual parsing)
        message.setSender("parsedSender");
        message.setReceiver("parsedReceiver");
        message.setTransactionReferenceNumber("parsedTransactionReferenceNumber");
        message.setValueDateCurrencyAmount("parsedValueDateCurrencyAmount");
        message.setOrderingCustomer("parsedOrderingCustomer");
        message.setBeneficiaryCustomer("parsedBeneficiaryCustomer");
        message.setRemittanceInformation("parsedRemittanceInformation");
        return message;
    }

}
