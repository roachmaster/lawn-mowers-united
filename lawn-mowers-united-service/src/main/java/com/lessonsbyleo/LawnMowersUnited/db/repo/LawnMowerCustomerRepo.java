package com.lessonsbyleo.LawnMowersUnited.db.repo;

import com.lessonsbyleo.LawnMowersUnited.db.entity.LawnMowerCustomerEntity;
import com.lessonsbyleo.LawnMowersUnited.db.entity.LawnMowerCustomerKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LawnMowerCustomerRepo extends JpaRepository<LawnMowerCustomerEntity, String> {
    LawnMowerCustomerEntity findLawnMowerCustomerEntityByLawnMowerCustomerKey(LawnMowerCustomerKey lawnMowerCustomerKey);
}
