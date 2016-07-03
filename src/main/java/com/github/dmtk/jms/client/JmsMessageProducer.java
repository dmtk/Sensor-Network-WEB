package com.github.dmtk.jms.client;

import javax.annotation.PostConstruct;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
 
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.logging.log4j.Logger;
 
 
 
import org.apache.logging.log4j.LogManager;


public class JmsMessageProducer {

    private static final Logger logger = LogManager.getLogger(JmsMessageProducer.class);

    protected static final String MESSAGE_COUNT = "messageCount";

    
    private JmsTemplate template = null;
    private int messageCount = 100;

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public void setTemplate(JmsTemplate template) {
        this.template = template;
    }

    public JmsTemplate getTemplate() {
        return template;
    }

    /**
     * Generates JMS messages
     * 
     */
    @PostConstruct
    public void generateMessages(){
        for (int i = 0; i < messageCount; i++) {
            final int index = i;
            final String text = "Message number is " + i + ".";

            template.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    TextMessage message = session.createTextMessage(text);
                    message.setIntProperty(MESSAGE_COUNT, index);
                    
                    logger.info("Sending message: " + text);
                    
                    return message;
                }
            });
        }
    }

}