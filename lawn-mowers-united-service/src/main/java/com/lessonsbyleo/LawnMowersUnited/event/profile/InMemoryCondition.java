package com.lessonsbyleo.LawnMowersUnited.event.profile;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.List;

public class InMemoryCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return isInMemoryProfile(context);
    }

    private boolean isInMemoryProfile(ConditionContext context) {
        List<String> list = List.of(context.getEnvironment().getActiveProfiles());
        boolean isInMemory = false;
        for(String env: list){
            if(env.equals("memory")){
                isInMemory = true;
            }
        }
        return isInMemory;
    }
}
