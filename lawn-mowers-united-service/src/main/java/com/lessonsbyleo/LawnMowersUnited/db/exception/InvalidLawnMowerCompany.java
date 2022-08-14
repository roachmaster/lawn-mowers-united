package com.lessonsbyleo.LawnMowersUnited.db.exception;

public class InvalidLawnMowerCompany extends Exception {
    private String lawnMowerCompanyEmail;

    public InvalidLawnMowerCompany(String lawnMowerCompanyEmail) {
        this.lawnMowerCompanyEmail = lawnMowerCompanyEmail;
    }

    public String getLawnMowerCompanyEmail() {
        return lawnMowerCompanyEmail;
    }

    public void setLawnMowerCompanyEmail(String lawnMowerCompanyEmail) {
        this.lawnMowerCompanyEmail = lawnMowerCompanyEmail;
    }
}
