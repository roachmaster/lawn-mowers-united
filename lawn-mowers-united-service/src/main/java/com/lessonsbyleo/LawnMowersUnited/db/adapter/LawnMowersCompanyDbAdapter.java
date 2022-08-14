package com.lessonsbyleo.LawnMowersUnited.db.adapter;

import com.lessonsbyleo.LawnMowersUnited.data.LawnMowerCompany;
import com.lessonsbyleo.LawnMowersUnited.db.entity.LawnMowerCompanyEntity;
import com.lessonsbyleo.LawnMowersUnited.db.exception.InvalidLawnMowerCompany;
import com.lessonsbyleo.LawnMowersUnited.db.factory.LawnMowerCompanyEntityFactory;
import com.lessonsbyleo.LawnMowersUnited.db.repo.LawnMowerCompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class LawnMowersCompanyDbAdapter implements LawnMowersCompanyDbAdapterInf {
    @Autowired
    private LawnMowerCompanyRepo lawnMowerCompanyRepo;

    @Override
    public void delete(LawnMowerCompany lawnMowerCompany) {
        LawnMowerCompanyEntity lawnMowerCompanyEntity = LawnMowerCompanyEntityFactory.create(lawnMowerCompany);
        lawnMowerCompanyRepo.delete(lawnMowerCompanyEntity);
    }

    @Override
    public boolean doesAccountExist(LawnMowerCompany lawnMowerCompany){
        LawnMowerCompanyEntity lawnMowerCompanyEntity = LawnMowerCompanyEntityFactory.create(lawnMowerCompany);
        LawnMowerCompanyEntity lawnMowerCompanyEntityFound = lawnMowerCompanyRepo.findAllByLawnMowerCompanyKey(lawnMowerCompanyEntity.getLawnMowerCompanyKey());
        return Objects.nonNull(lawnMowerCompanyEntityFound);
    }

    @Override
    public void createAccount(LawnMowerCompany lawnMowerCompany) {
        LawnMowerCompanyEntity lawnMowerCompanyEntity = LawnMowerCompanyEntityFactory.create(lawnMowerCompany);
        lawnMowerCompanyRepo.save(lawnMowerCompanyEntity);
    }

    @Override
    public List<LawnMowerCompany> getLawnMowerAccounts() {
        List<LawnMowerCompanyEntity> entities = lawnMowerCompanyRepo.findAll();
        List<LawnMowerCompany> lawnMowerCompanies = new ArrayList<>();
        for(LawnMowerCompanyEntity entity: entities){
            LawnMowerCompany lawnMowerCompany = new LawnMowerCompany(entity);
            lawnMowerCompanies.add(lawnMowerCompany);
        }
        return lawnMowerCompanies;
    }

    @Override
    public void deleteAll() {
        lawnMowerCompanyRepo.deleteAll();
    }

    @Override
    public LawnMowerCompany getAccount(String email) throws InvalidLawnMowerCompany {
        LawnMowerCompanyEntity entity = lawnMowerCompanyRepo.findLawnMowerCompanyEntityByLawnMowerCompanyKey_Email(email);
        if(Objects.isNull(entity)){
            throw new InvalidLawnMowerCompany(email);
        }
        return new LawnMowerCompany(entity);
    }
}
