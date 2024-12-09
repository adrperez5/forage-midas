package com.jpmc.midascore.repository;

import com.jpmc.midascore.entity.TransactionRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<TransactionRecord, Long> {

    TransactionRecord findById(long transactionId);

    List<TransactionRecord> findBySenderId(long senderId);

    List<TransactionRecord> findBySenderIdAndRecipientId(long senderId, long recipientId);
}
