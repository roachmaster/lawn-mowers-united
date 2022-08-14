package com.lessonsbyleo.LawnMowersUnited.data;

import com.lessonsbyleo.LawnMowersUnited.db.entity.LawnMowerCompanyEntity;

import java.util.Objects;

public class LawnMowerCompany {
    private String companyName;
    private String companyEmail;

    public LawnMowerCompany() {
    }

    public LawnMowerCompany(String companyName, String companyEmail) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
    }

    public LawnMowerCompany(LawnMowerCompanyEntity lawnMowerCompanyEntity){
        this.companyName = lawnMowerCompanyEntity.getLawnMowerCompanyKey().getName();
        this.companyEmail = lawnMowerCompanyEntity.getLawnMowerCompanyKey().getEmail();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LawnMowerCompany that = (LawnMowerCompany) o;
        return Objects.equals(companyName, that.companyName) && Objects.equals(companyEmail, that.companyEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, companyEmail);
    }

    @Override
    public String toString() {
        return "LawnMowerCompany{" +
                "companyName='" + companyName + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                '}';
    }
}
