package com.lessonsbyleo.LawnMowersUnited.db.factory;

import com.lessonsbyleo.LawnMowersUnited.data.LawnMowerCompany;
import com.lessonsbyleo.LawnMowersUnited.db.entity.LawnMowerCompanyEntity;
import com.lessonsbyleo.LawnMowersUnited.db.entity.LawnMowerCompanyKey;

import java.util.ArrayList;
import java.util.List;

public class LawnMowerCompanyEntityFactory {
    public static LawnMowerCompanyEntity create(LawnMowerCompany lawnMowerCompany) {
        LawnMowerCompanyKey lawnMowerCompanyKey = new LawnMowerCompanyKey();
        lawnMowerCompanyKey.setName(lawnMowerCompany.getCompanyName());
        lawnMowerCompanyKey.setEmail(lawnMowerCompany.getCompanyEmail());
        return new LawnMowerCompanyEntity(lawnMowerCompanyKey);
    }

    public static List<LawnMowerCompanyEntity> create(List<LawnMowerCompany> lawnMowerCompanyList) {
        List<LawnMowerCompanyEntity> lawnMowerCompanies = new ArrayList<>();
        for(LawnMowerCompany lawnMowerCompany: lawnMowerCompanyList){
            lawnMowerCompanies.add(create(lawnMowerCompany));
        }
        return lawnMowerCompanies;
    }
}
