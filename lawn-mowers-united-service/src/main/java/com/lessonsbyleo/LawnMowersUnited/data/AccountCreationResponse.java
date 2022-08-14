package com.lessonsbyleo.LawnMowersUnited.data;

public class AccountCreationResponse {
    private final AccountCreationState accountCreationState;

    public AccountCreationResponse(AccountCreationState accountCreationState) {
        this.accountCreationState = accountCreationState;
    }

    public AccountCreationState getState() {
        return accountCreationState;
    }
}
