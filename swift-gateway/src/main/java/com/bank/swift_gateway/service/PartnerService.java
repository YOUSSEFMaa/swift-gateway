package com.bank.swift_gateway.service;

import com.bank.swift_gateway.model.Partner;
import com.bank.swift_gateway.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    public Partner addPartner(Partner partner) {
        return partnerRepository.save(partner);
    }
}
