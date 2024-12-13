package org.example.loansservice.service.impl;


import jakarta.transaction.Transactional;
import org.example.loansservice.entity.Loan;
import org.example.loansservice.repository.LoanRepository;
import org.example.loansservice.rest.AccountServiceClient;
import org.example.loansservice.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private AccountServiceClient accountServiceClient;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    public List<Loan> getLoansByAccountId(Long accountId) {
        return loanRepository.findByAccountId(accountId);
    }

    public Loan saveLoan(Loan loan) {
        if (accountServiceClient.accountExists(loan.getAccountId())) {
            return loanRepository.save(loan);
        } else {
            throw new RuntimeException("Account does not exist");
        }
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }

    @Transactional
    public void deleteLoansByAccountId(Long accountId) {
        logger.info("Deleting loans associated with accountId: {}", accountId);
        loanRepository.deleteByAccountId(accountId);
    }

}

