package com.github.dmtk.jms.server;

/**
 *
 * @author dmytro
 */
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JmsMessageListener implements MessageListener {

    private static final Logger logger = LogManager.getLogger(JmsMessageListener.class);

    /**
     * Implementation of <code>MessageListener</code>.
     */
    public void onMessage(Message message) {
        try {
            int messageCount = message.getIntProperty("messageCount");
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                String msg = tm.getText();
                logger.info("Processed message '{}'.  value={}", msg, messageCount);
            }
        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
