package com.bank.swift_gateway.controller;

import com.bank.swift_gateway.model.Partner;
import com.bank.swift_gateway.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/partners")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @PostMapping
    public Partner addPartner(@RequestBody Partner partner) {
        return partnerService.addPartner(partner);
    }
}
