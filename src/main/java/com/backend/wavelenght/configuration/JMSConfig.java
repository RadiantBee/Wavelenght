package com.backend.wavelenght.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jms.autoconfigure.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.Topic;

@Configuration
@EnableJms
public class JMSConfig {

    @Value("${my.activemq.topic}")
    private String topic_name;  // elem-dest.topic

    @Bean
    public Topic elemTopicCreate (JmsTemplate jmsTemplate) throws JMSException {

        return jmsTemplate.getConnectionFactory()
                .createConnection()
                .createSession()
                .createTopic(topic_name);

                 // .createTopic("elem-dest.topic");
    }


    @Bean
    public JmsListenerContainerFactory<?> myFactory (ConnectionFactory connectionFactory,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        factory.setPubSubDomain(true);
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

}
