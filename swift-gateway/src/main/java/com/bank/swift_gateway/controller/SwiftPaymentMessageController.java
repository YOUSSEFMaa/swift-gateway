package com.bank.swift_gateway.controller;


import com.bank.swift_gateway.model.SwiftPaymentMessage103;
import com.bank.swift_gateway.service.SwiftPaymentMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mt")
public class SwiftPaymentMessageController {

    @Autowired
    private SwiftPaymentMessageService messageService;

    @GetMapping("/103")
    public Page<SwiftPaymentMessage103> getAllMessages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return messageService.getMessages(page, size);
    }
}
