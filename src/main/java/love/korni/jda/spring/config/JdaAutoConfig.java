/*
 * Copyright (c) 2022 Sergei Kornilov
 * Licensed under the Apache License, Version 2.0
 */

package love.korni.jda.spring.config;

import love.korni.jda.spring.JdaProperties;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.IEventManager;
import net.dv8tion.jda.api.hooks.InterfacedEventManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;

import java.util.List;
import java.util.Objects;

/**
 * Auto configuration for JDA.
 *
 * @author Sergei_Konilov
 */
@Configuration
@EnableConfigurationProperties(JdaProperties.class)
@ConditionalOnProperty(prefix = "spring.jda", value = "token")
public class JdaAutoConfig {

    @Bean
    @ConditionalOnMissingBean(type = "net.dv8tion.jda.api.hooks.IEventManager")
    public IEventManager eventManager() {
        return new InterfacedEventManager();
    }

    @Bean
    @ConditionalOnMissingBean(JDA.class)
    @ConditionalOnProperty(value = "spring.jda.auto-create", havingValue = "true", matchIfMissing = true)
    public JDA jda(JDABuilder jdaBuilder) {
        return jdaBuilder.build();
    }

    @Bean
    @ConditionalOnProperty(value = "spring.jda.auto-create", havingValue = "true", matchIfMissing = true)
    public JDABuilder jdaBuilder(JdaProperties jdaProperties,
                                 IEventManager eventManager,
                                 List<EventListener> eventListeners) {
        JDABuilder builder = JDABuilder.createDefault(jdaProperties.getToken());

        if (Objects.nonNull(jdaProperties.getActivity())) {
            Activity activity = Activity.of(jdaProperties.getActivity().getType(), jdaProperties.getActivity().getActivity());
            builder.setActivity(activity);
        }

        builder.setEventManager(eventManager);
        if (!eventListeners.isEmpty()) {
            builder.addEventListeners(eventListeners.toArray());
        }

        return builder;
    }

    @Bean
    @ConditionalOnBean(JDA.class)
    @ConditionalOnMissingBean(type = "net.dv8tion.jda.api.entities.SelfUser")
    public SelfUser selfUser(JDA jda) {
        return jda.getSelfUser();
    }
}
