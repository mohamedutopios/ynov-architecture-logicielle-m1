package org.example.loansservice.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.loansservice.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LoanKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(LoanKafkaConsumer.class);
    private final LoanService loanService;

    public LoanKafkaConsumer(LoanService loanService) {
        this.loanService = loanService;
    }

    @KafkaListener(topics = "account-events", groupId = "loan-group")
    public void consumeAccountDeletedEvent(String message) {
        logger.info("Received account deleted event: {}", message);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode event = objectMapper.readTree(message);

            if ("ACCOUNT_DELETED".equals(event.get("event").asText())) {
                String accountIdStr = event.get("accountId").asText();
                Long accountId = Long.valueOf(accountIdStr);
                logger.info("Processing deletion for accountId: {}", accountId);
                loanService.deleteLoansByAccountId(accountId);
            }
        } catch (Exception e) {
            logger.error("Error processing account deleted event", e);
        }
    }
}



