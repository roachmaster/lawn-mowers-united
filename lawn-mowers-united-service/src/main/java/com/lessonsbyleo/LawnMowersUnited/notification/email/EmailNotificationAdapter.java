package com.lessonsbyleo.LawnMowersUnited.notification.email;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lessonsbyleo.LawnMowersUnited.data.LawnMowerCompany;
import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEvent;
import com.lessonsbyleo.LawnMowersUnited.notification.LawnMowersUnitedNotification;
import com.lessonsbyleo.LawnMowersUnited.notification.LawnMowersUnitedNotificationAdapter;
import com.lessonsbyleo.LawnMowersUnited.notification.LawnMowersUnitedNotificationFactory;
import com.lessonsbyleo.LawnMowersUnited.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationAdapter implements LawnMowersUnitedNotificationAdapter {
    private static final ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private EmailSender emailSender;

    public void sendEmail(LawnMowersUnitedNotification lawnMowersUnitedNotification) throws JsonProcessingException {
        logger.info("Sending Email: \n{}", prettyPrinter.writeValueAsString(lawnMowersUnitedNotification));
        emailSender.send(lawnMowersUnitedNotification);
    }

    @Override
    public void notifyLawnMowerCompany(LawnMowerCompany lawnMowerCompany, LawnMowersUnitedEvent lawnMowersUnitedEvent) {
        LawnMowersUnitedNotification lawnMowersUnitedNotification = new LawnMowersUnitedNotification();
        lawnMowersUnitedNotification.setEmail(lawnMowerCompany.getCompanyEmail());
        lawnMowersUnitedNotification.setDescription(lawnMowersUnitedEvent.getDescription());
        notifyLawnMowerCompany(lawnMowersUnitedNotification);
    }

    @Override
    public void notifyLawnMowerCompany(LawnMowersUnitedNotification notification) {
        try {
            sendEmail(notification);
        } catch (JsonProcessingException e) {
            logger.error("unable to send email to {}", notification.getEmail());
        }
    }

    @Override
    public void notifyAdmin(LawnMowersUnitedEvent lawnMowersUnitedEvent) {
        String adminEmail = getAdminEmail();
        LawnMowersUnitedNotification notification = LawnMowersUnitedNotificationFactory.create(lawnMowersUnitedEvent, adminEmail);
        try {
            sendEmail(notification);
        } catch (JsonProcessingException e) {
            logger.error("unable to send email to admins {}", notification.getEmail());
        }
    }

    private String getAdminEmail() {
        return "admin@admin.com";
    }
}
