package com.lessonsbyleo.LawnMowersUnited.notification;

import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEvent;

public class LawnMowersUnitedNotificationFactory {
    public static LawnMowersUnitedNotification create(LawnMowersUnitedEvent lawnMowersUnitedEvent){
        LawnMowersUnitedNotification notification = new LawnMowersUnitedNotification();
        notification.setEmail(lawnMowersUnitedEvent.getEmail());
        notification.setDescription(lawnMowersUnitedEvent.getDescription());
        return notification;
    }

    public static LawnMowersUnitedNotification create(LawnMowersUnitedEvent lawnMowersUnitedEvent, String adminEmail) {
        LawnMowersUnitedNotification notification = create(lawnMowersUnitedEvent);
        notification.setEmail(adminEmail);
        return notification;
    }
}
