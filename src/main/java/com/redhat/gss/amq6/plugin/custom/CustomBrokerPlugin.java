package com.redhat.gss.amq6.plugin.custom;

import java.nio.charset.StandardCharsets;

import org.apache.activemq.broker.BrokerPluginSupport;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.command.Message;
import org.apache.activemq.command.MessageDispatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomBrokerPlugin extends BrokerPluginSupport {

    private final static Logger log = LoggerFactory.getLogger(CustomBrokerPlugin.class);


    @Override
    public void postProcessDispatch(MessageDispatch messageDispatch) {
        if(messageDispatch != null) {
            super.postProcessDispatch(messageDispatch);
            final Message message = messageDispatch.getMessage();
            if (message instanceof ActiveMQTextMessage) {
                if (message != null && message.getContent() != null) {
                    try {
                        log.info("Messaggio POST PROCESS in TEXT scodato dalla coda: \"{}\", con testo: \"{}\"",
                                ((ActiveMQTextMessage) message).getDestination().toString(),
                                ((ActiveMQTextMessage) message).getText());
                    } catch (final Exception e) {
                        log.trace("Impossibile recuperare le informazioni sul testo del messaggio in arrivo in coda {}", message, e);
                    }
                }
            } else if (message instanceof ActiveMQMessage) {
                if (message != null && message.getContent() != null) {
                    byte[] bytes = message.getContent().getData();
                    String text = new String(bytes, StandardCharsets.UTF_8);
                    log.info("Messaggio POST PROCESS in BYTE scodato dalla coda: \"{}\", con testo: \"{}\"",
                            ((ActiveMQMessage) message).getDestination().toString(), text);
                }
            }
        }
    }

}