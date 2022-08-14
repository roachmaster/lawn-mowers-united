package com.lessonsbyleo.LawnMowersUnited.bdd;

import com.lessonsbyleo.LawnMowersUnited.event.publisher.LawnMowerCustomerServiceRequestEventPublisher;
import com.lessonsbyleo.LawnMowersUnited.event.publisher.NewAccountCreationEventPublisher;
import com.lessonsbyleo.LawnMowersUnited.notification.email.EmailSender;
import com.lessonsbyleo.LawnMowersUnited.notification.LawnMowersUnitedNotificationAdapter;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"com.lessonsbyleo.LawnMowersUnited"}
/*
        ,excludeFilters = { @ComponentScan.Filter(

                type = FilterType.ASSIGNABLE_TYPE, value = {
                        //list of Filtered out classes
})}
*/
)
@Configuration
public class LawnMowersUnitedBddConfig {
    @Bean
    public NewAccountCreationEventPublisher newAccountCreationEventPublisher(){
        return Mockito.mock(NewAccountCreationEventPublisher.class);
    }

    @Bean
    public LawnMowerCustomerServiceRequestEventPublisher lawnMowerCustomerServiceRequestEventPublisher(){
        return Mockito.mock(LawnMowerCustomerServiceRequestEventPublisher.class);
    }

    @Bean
    public LawnMowersUnitedNotificationAdapter mockLawnMowersUnitedNotificationAdapter(){
        return Mockito.mock(LawnMowersUnitedNotificationAdapter.class);
    }
    @Bean
    public EmailSender emailSender(){
        return Mockito.mock(EmailSender.class);
    }

}
