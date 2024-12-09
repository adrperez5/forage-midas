package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.repository.TransactionRepository;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConduit {

    private final Logger LOGGER = LoggerFactory.getLogger(DatabaseConduit.class);

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public DatabaseConduit(UserRepository userRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;

    }

    public void saveUser(UserRecord userRecord) {
        userRepository.save(userRecord);
    }

    @Transactional
    public void validateTransaction(long senderId, long recipientId, float amount) {

        if (!userRepository.existsById(senderId) || !userRepository.existsById(recipientId)) {
            LOGGER.info("A user specified does not exist... discarding transaction");
            return;
        }

        float balance = userRepository.findBalanceById(senderId);

        if (balance >= amount) {
            LOGGER.info("Processing transaction...");

            // Update records and save transaction
            userRepository.updateUserBalance(senderId, balance - amount);
            userRepository.updateUserBalance(recipientId, userRepository.findBalanceById(recipientId) + amount);
            transactionRepository.save(new TransactionRecord(senderId, recipientId, amount));

            LOGGER.info("Processed transaction");

        } else
            LOGGER.info("Does not meed the required funds... Discarding transaction");
    }
}