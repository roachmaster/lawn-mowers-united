package com.lessonsbyleo.LawnMowersUnited.db.adapter;

import com.lessonsbyleo.LawnMowersUnited.data.Customer;
import com.lessonsbyleo.LawnMowersUnited.db.entity.LawnMowerCustomerEntity;
import com.lessonsbyleo.LawnMowersUnited.db.factory.LawnMowerCustomerEntityFactory;
import com.lessonsbyleo.LawnMowersUnited.db.repo.LawnMowerCustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LawnMowerCustomerDBAdapter implements LawnMowerCustomerDBAdapterInf {
    @Autowired
    private LawnMowerCustomerRepo lawnMowerCustomerRepo;

    @Override
    public void delete(Customer customer) {
        LawnMowerCustomerEntity lawnMowerCustomerEntity = LawnMowerCustomerEntityFactory.create(customer);
        lawnMowerCustomerRepo.delete(lawnMowerCustomerEntity);
    }

    @Override
    public boolean doesAccountExistFor(Customer customer) {
        LawnMowerCustomerEntity lawnMowerCustomerEntity = LawnMowerCustomerEntityFactory.create(customer);
        LawnMowerCustomerEntity foundLawnMowerCustomerEntity = lawnMowerCustomerRepo.findLawnMowerCustomerEntityByLawnMowerCustomerKey(lawnMowerCustomerEntity.getLawnMowerCustomerKey());
        return Objects.nonNull(foundLawnMowerCustomerEntity);
    }

    @Override
    public void createAccount(Customer customer) {
        LawnMowerCustomerEntity lawnMowerCustomerEntity = LawnMowerCustomerEntityFactory.create(customer);
        lawnMowerCustomerRepo.save(lawnMowerCustomerEntity);
    }
}
