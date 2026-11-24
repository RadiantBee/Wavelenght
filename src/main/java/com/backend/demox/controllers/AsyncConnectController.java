package com.backend.demox.controllers;


import com.backend.demox.models.Element;
import org.apache.activemq.ActiveMQConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.jms.*;

@RestController
@RequestMapping("async")
public class AsyncConnectController {

    private static Logger LOG = LoggerFactory.getLogger(AsyncConnectController.class);

    @Autowired
    private JmsTemplate jmsTemplate;


    @GetMapping("/send")
    public String SendTest() {
        try {
            jmsTemplate.convertAndSend("elem-dest", "Hello World!");
            LOG.info("JMS message sent");
            return "OK";
        } catch (JmsException ex) {
            ex.printStackTrace();
            LOG.error("JMS Error");
            return "FAIL";
        }
    }

    @GetMapping("/send-obj")
    public String SendObj() {
        try{

            Element el = new Element(999L, "testName", 999);
            jmsTemplate.convertAndSend("elem-dest", el);
            LOG.info("sent JMS object: "+el.hashCode());
            return "OK: " + el.hashCode() ;
        }catch(JmsException ex){
            ex.printStackTrace();
            LOG.error("JMS Error");
            return "FAIL";
        }
    }

    // Sending "high priority" message

    @GetMapping("/send-obj-hp")
    public String SendObjHP() {
        try{

            Element el = new Element(888L, "testName", 888);

            jmsTemplate.convertAndSend("elem-dest", el,
                    messagePostProcessor ->
                    { messagePostProcessor.setStringProperty("priority",
                        "high");
                      return messagePostProcessor;
                    });

            LOG.info("sent high priority JMS object: "+el.hashCode());
            return "OK: " + el.hashCode() ;
        }catch(JmsException ex){
            ex.printStackTrace();
            LOG.error("JMS Error");
            return "FAIL";
        }
    }


    // Generalnie to  użycie POSTa
    @GetMapping("/send-map-message")
    public String SendMapMessage() {
        try{

            Session session = jmsTemplate.getConnectionFactory()
                    .createConnection()
                    .createSession(false, Session.AUTO_ACKNOWLEDGE);

            // sesja nietranzakcyjna  z automatycznym protwierdzeniem

            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setInt("Key_Id", 123);
            mapMessage.setString("Key_Name", "Name 123");

            mapMessage.setStringProperty("priority", "high");
            mapMessage.setStringProperty("EVENT_TYPE", "TYPE1");
            mapMessage.setStringProperty("EVENT_SYSTEM", "SYSTEM1");

            jmsTemplate.send("elem-dest", new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return mapMessage;
                }
            });

            LOG.info("sent map message: " + mapMessage.hashCode());
            return "OK: " + mapMessage.hashCode() ;
        }catch(JmsException ex){
            ex.printStackTrace();
            LOG.error("JMS Error");
            return "FAIL";
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }



    @Autowired
    private Topic elemTopic;

    @GetMapping("/send-to-topic")
    public String SendToTopic() {
        try {
            jmsTemplate.convertAndSend(elemTopic, "Hello World 4 topic");
            LOG.info("JMS message sent");
            return "OK";
        } catch (JmsException ex) {
            ex.printStackTrace();
            LOG.error("JMS Error");
            return "FAIL";
        }
    }

}



