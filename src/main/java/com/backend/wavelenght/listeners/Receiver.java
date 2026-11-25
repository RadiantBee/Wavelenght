package com.backend.wavelenght.listeners;

import com.backend.wavelenght.controllers.AsyncConnectController;
import com.backend.wavelenght.models.Element;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import jakarta.jms.JMSException;
import jakarta.jms.MapMessage;
import jakarta.jms.Topic;
import java.util.Enumeration;

@Component
@Profile("jmslistener")
public class Receiver {

    private static Logger LOG = LoggerFactory.getLogger(Receiver.class);

    // @JmsListener(destination = "elem-dest", containerFactory = "myFactory")
    public void receiveMessage(String s) {
        LOG.info("Receiving data");
        System.out.println("Received <" + s + ">");
    }

    // @JmsListener(destination = "elem-dest", containerFactory = "myFactory")
    public void receiveObject(Element el) {
        LOG.info("Received element:"  + el.hashCode());
    }


    //@JmsListener(destination = "elem-dest", containerFactory = "myFactory", selector = "priority = 'high'")
    public void receiveObjHighPriority(Element el) {
        LOG.info("Received element:"  + el.hashCode());
    }



    public class Part {
        private   int Key_Id;
        private  String Key_Name;

        @Override
        public String toString() {
            return "Part{" +
                    "Key_Id=" + Key_Id +
                    ", Key_Name='" + Key_Name + '\'' +
                    '}';
        }
    }

    @JmsListener(destination = "elem-dest", containerFactory = "myFactory")
    public void receiveObjPri(MapMessage mapMessage) throws JMSException {

        LOG.info("Received element:"  + mapMessage.hashCode());
        LOG.info("Map message key Key_Id: "+ mapMessage.getInt("Key_Id"));
        LOG.info("Map message Key_Name: "+ mapMessage.getString("Key_Name"));

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();

        @SuppressWarnings("unchecked")
        Enumeration<String> mapNames = mapMessage.getMapNames();
        while (mapNames.hasMoreElements()) {
            String field = mapNames.nextElement();
            root.set(field, mapper.convertValue(mapMessage.getObject(field), JsonNode.class));
            LOG.info("Key: "+ field + " Value: "+ mapMessage.getObject(field));
        }

        String JsonString ;
        try {
            // zapis  obiektu JSONa do String
            JsonString = mapper.writer().writeValueAsString(root);
            LOG.info(JsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Wytworzenie obiektu POJO z łańcucha tekstowego w zawartością w formacie JSON -  użycie Gson;
        Gson gson = new Gson();
        Part  part = gson.fromJson(JsonString, Part.class);
        LOG.info(part.toString());

        return;
    }


    /*
    //@JmsListener(destination = "elem-dest.topic", containerFactory = "myFactory")
    public void receiveMessageFromTopic(String s) {
        LOG.info("Receiving data");
        System.out.println("Received <" + s + ">");
    }
    */

}
