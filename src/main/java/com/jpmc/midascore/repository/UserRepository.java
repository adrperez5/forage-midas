package com.jpmc.midascore.repository;

import com.jpmc.midascore.entity.UserRecord;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserRecord, Long> {
    UserRecord findById(long id);

    @Query("SELECT u.balance FROM UserRecord u WHERE u.id = :id")
    Float findBalanceById(long id);

    @Modifying
    @Query("UPDATE UserRecord u SET u.balance = :balance WHERE u.id = :id")
    void updateUserBalance(long id, float balance);
}
