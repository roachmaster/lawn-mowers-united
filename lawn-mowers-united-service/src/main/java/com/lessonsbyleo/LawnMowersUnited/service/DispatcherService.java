package com.lessonsbyleo.LawnMowersUnited.service;

import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEvent;
import com.lessonsbyleo.LawnMowersUnited.notification.LawnMowersUnitedNotification;
import com.lessonsbyleo.LawnMowersUnited.notification.LawnMowersUnitedNotificationAdapter;
import com.lessonsbyleo.LawnMowersUnited.notification.LawnMowersUnitedNotificationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispatcherService {

    @Autowired
    private LawnMowersUnitedNotificationAdapter notificationAdapter;
    public void dispatch(LawnMowersUnitedEvent lawnMowersUnitedEvent) {
        LawnMowersUnitedNotification notification = LawnMowersUnitedNotificationFactory.create(lawnMowersUnitedEvent);
        notificationAdapter.notifyLawnMowerCompany(notification);
    }
}
