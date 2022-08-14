package com.lessonsbyleo.LawnMowersUnited.notification.email;

import com.lessonsbyleo.LawnMowersUnited.notification.LawnMowersUnitedNotification;

public interface EmailSender {
    void send(LawnMowersUnitedNotification lawnMowersUnitedNotification);
}
