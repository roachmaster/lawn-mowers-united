package com.lessonsbyleo.LawnMowersUnited.service;

import com.lessonsbyleo.LawnMowersUnited.data.LawnMowerCompany;
import com.lessonsbyleo.LawnMowersUnited.db.adapter.LawnMowersCompanyDbAdapterInf;
import com.lessonsbyleo.LawnMowersUnited.db.exception.InvalidLawnMowerCompany;
import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEvent;
import com.lessonsbyleo.LawnMowersUnited.notification.LawnMowersUnitedNotificationAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.lessonsbyleo.LawnMowersUnited.data.ServiceRequestState.INVALID_LAWN_MOWER_COMPANY;
@Service
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    @Autowired
    LawnMowersUnitedNotificationAdapter lawnMowersUnitedNotificationAdapter;
    @Autowired
    LawnMowersCompanyDbAdapterInf lawnMowersCompanyDbAdapter;

    public void notifyLawnMowerCompany(LawnMowersUnitedEvent lawnMowersUnitedEvent) {
        String email = lawnMowersUnitedEvent.getEmail();
        try {
            LawnMowerCompany lawnMowerCompany = lawnMowersCompanyDbAdapter.getAccount(email);
            lawnMowersUnitedNotificationAdapter.notifyLawnMowerCompany(lawnMowerCompany, lawnMowersUnitedEvent);
        } catch (InvalidLawnMowerCompany e) {
            logger.error("Invalid Lawn Mower Company email: {}", email);
            lawnMowersUnitedEvent.setType(INVALID_LAWN_MOWER_COMPANY.name());
            lawnMowersUnitedNotificationAdapter.notifyAdmin(lawnMowersUnitedEvent);
        }
    }
}
