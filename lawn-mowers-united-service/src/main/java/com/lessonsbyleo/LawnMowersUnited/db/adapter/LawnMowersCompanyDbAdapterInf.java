package com.lessonsbyleo.LawnMowersUnited.db.adapter;

import com.lessonsbyleo.LawnMowersUnited.data.LawnMowerCompany;
import com.lessonsbyleo.LawnMowersUnited.db.exception.InvalidLawnMowerCompany;

import java.util.List;

public interface LawnMowersCompanyDbAdapterInf {
    void delete(LawnMowerCompany lawnMowerCompany);
    boolean doesAccountExist(LawnMowerCompany lawnMowerCompany);
    void createAccount(LawnMowerCompany lawnMowerCompany);

    List<LawnMowerCompany> getLawnMowerAccounts();

    void deleteAll();

    LawnMowerCompany getAccount(String email) throws InvalidLawnMowerCompany;
}
