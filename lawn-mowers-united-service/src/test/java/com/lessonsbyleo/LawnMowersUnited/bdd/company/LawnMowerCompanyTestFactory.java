package com.lessonsbyleo.LawnMowersUnited.bdd.company;

import com.lessonsbyleo.LawnMowersUnited.data.LawnMowerCompany;

import java.util.ArrayList;
import java.util.List;


public class LawnMowerCompanyTestFactory {
    public static List<LawnMowerCompany> create(int num){
        List<LawnMowerCompany> lawnMowerCompanyList = new ArrayList<>();
        for(int i = 0; i < num; i++){
            String lawnMowerCompanyName = "Some Lawn Mower Company" + i;
            String lawnMowerCompanyEmail= "someLawnMowerCompany@email.com" + i;
            LawnMowerCompany lawnMowerCompany = new LawnMowerCompany(lawnMowerCompanyName, lawnMowerCompanyEmail);
            lawnMowerCompanyList.add(lawnMowerCompany);
        }
        return lawnMowerCompanyList;
    }
}
