package com.bank.swift_gateway.service;

import com.bank.swift_gateway.model.SwiftPaymentMessage103;
import com.bank.swift_gateway.repository.SwiftPaymentMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SwiftPaymentMessageServiceTest {

    @Mock
    private SwiftPaymentMessageRepository repository;

    @InjectMocks
    private SwiftPaymentMessageService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMessages() {
        Page<SwiftPaymentMessage103> page = new PageImpl<>(Collections.singletonList(new SwiftPaymentMessage103()));
        when(repository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<SwiftPaymentMessage103> result = service.getMessages(0, 10);

        assertEquals(1, result.getTotalElements());
        verify(repository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void testSaveMessage() {
        SwiftPaymentMessage103 message = new SwiftPaymentMessage103();
        when(repository.save(any(SwiftPaymentMessage103.class))).thenReturn(message);

        service.saveMessage("rawMessage");

        verify(repository, times(1)).save(any(SwiftPaymentMessage103.class));
    }
}