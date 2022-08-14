package com.lessonsbyleo.LawnMowersUnited.notification;

import com.lessonsbyleo.LawnMowersUnited.data.LawnMowerCompany;
import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEvent;
import org.springframework.stereotype.Component;

public interface LawnMowersUnitedNotificationAdapter {
    void notifyLawnMowerCompany(LawnMowerCompany lawnMowerCompany, LawnMowersUnitedEvent lawnMowersUnitedEvent);

    void notifyLawnMowerCompany(LawnMowersUnitedNotification notification);

    void notifyAdmin(LawnMowersUnitedEvent lawnMowersUnitedEvent);
}
