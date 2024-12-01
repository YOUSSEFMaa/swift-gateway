package com.bank.swift_gateway.repository;


import com.bank.swift_gateway.model.SwiftPaymentMessage103;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwiftPaymentMessageRepository extends JpaRepository<SwiftPaymentMessage103, Long> {
}