package com.lessonsbyleo.LawnMowersUnited.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lessonsbyleo.LawnMowersUnited.data.LawnMowerCompany;
import com.lessonsbyleo.LawnMowersUnited.event.LawnMowersUnitedEvent;
import com.lessonsbyleo.LawnMowersUnited.notification.email.EmailNotificationAdapter;
import io.cucumber.java.Scenario;
import org.mockito.ArgumentCaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Primary
@Component
public class EmailNotificationAdapterTestDouble implements LawnMowersUnitedNotificationAdapter{
    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationAdapterTestDouble.class);

    @Autowired
    private LawnMowersUnitedNotificationAdapter mockLawnMowersUnitedNotificationAdapter;
    @Autowired
    private EmailNotificationAdapter emailNotificationAdapter;

    private ArgumentCaptor<LawnMowerCompany> lawnMowerCompanyArgumentCaptor = ArgumentCaptor.forClass(LawnMowerCompany.class);
    private ArgumentCaptor<LawnMowersUnitedEvent> lawnMowersUnitedEventArgumentCaptor = ArgumentCaptor.forClass(LawnMowersUnitedEvent.class);
    @Override
    public void notifyLawnMowerCompany(LawnMowerCompany lawnMowerCompany, LawnMowersUnitedEvent lawnMowersUnitedEvent) {
        logger.info("notifyLawnMowerCompany");
        emailNotificationAdapter.notifyLawnMowerCompany(lawnMowerCompany, lawnMowersUnitedEvent);
        mockLawnMowersUnitedNotificationAdapter.notifyLawnMowerCompany(lawnMowerCompany, lawnMowersUnitedEvent);
    }

    @Override
    public void notifyLawnMowerCompany(LawnMowersUnitedNotification notification) {
        logger.info("notifyLawnMowerCompany");
        emailNotificationAdapter.notifyLawnMowerCompany(notification);
        mockLawnMowersUnitedNotificationAdapter.notifyLawnMowerCompany(notification);
    }

    @Override
    public void notifyAdmin(LawnMowersUnitedEvent lawnMowersUnitedEvent) {
        logger.info("notifyAdmin");
        emailNotificationAdapter.notifyAdmin(lawnMowersUnitedEvent);
        mockLawnMowersUnitedNotificationAdapter.notifyAdmin(lawnMowersUnitedEvent);
    }

    public void captureArgumentsNotifyLawnMowerCompanyEvent(ObjectWriter prettyWriter, Scenario scenario) throws JsonProcessingException {
        verify(mockLawnMowersUnitedNotificationAdapter, times(1)).notifyLawnMowerCompany(lawnMowerCompanyArgumentCaptor.capture(),lawnMowersUnitedEventArgumentCaptor.capture());
        LawnMowerCompany actualLawnMowerCompany = lawnMowerCompanyArgumentCaptor.getValue();
        String actualLawnMowerCompanyString = prettyWriter.writeValueAsString(actualLawnMowerCompany);
        scenario.log(actualLawnMowerCompanyString);
        String lawnMowerServiceRequestEventDescription = prettyWriter.writeValueAsString(lawnMowersUnitedEventArgumentCaptor.getValue());
        scenario.log(lawnMowerServiceRequestEventDescription);
    }
}
