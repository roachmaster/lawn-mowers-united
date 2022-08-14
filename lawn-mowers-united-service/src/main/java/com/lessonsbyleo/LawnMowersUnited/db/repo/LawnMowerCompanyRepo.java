package com.lessonsbyleo.LawnMowersUnited.db.repo;

import com.lessonsbyleo.LawnMowersUnited.db.entity.LawnMowerCompanyEntity;
import com.lessonsbyleo.LawnMowersUnited.db.entity.LawnMowerCompanyKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LawnMowerCompanyRepo extends JpaRepository<LawnMowerCompanyEntity, String> {
    LawnMowerCompanyEntity findAllByLawnMowerCompanyKey(LawnMowerCompanyKey lawnMowerCompanyKey);
    LawnMowerCompanyEntity findLawnMowerCompanyEntityByLawnMowerCompanyKey_Email(String email);
}
