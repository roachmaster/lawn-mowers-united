package com.lessonsbyleo.LawnMowersUnited.notification.email;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lessonsbyleo.LawnMowersUnited.event.profile.InMemoryCondition;
import com.lessonsbyleo.LawnMowersUnited.notification.LawnMowersUnitedNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(InMemoryCondition.class)
public class InMemoryEmailSender implements EmailSender{
    private static final Logger logger = LoggerFactory.getLogger(InMemoryEmailSender.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
    @Override
    public void send(LawnMowersUnitedNotification lawnMowersUnitedNotification) {
        try {
            logger.info("Sending Email: {}", objectWriter.writeValueAsString(lawnMowersUnitedNotification));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
