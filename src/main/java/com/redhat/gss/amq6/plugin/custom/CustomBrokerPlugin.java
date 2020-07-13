package com.redhat.gss.amq6.plugin.custom;

import org.apache.activemq.broker.BrokerPluginSupport;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.command.Message;
import org.apache.activemq.command.MessageDispatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomBrokerPlugin extends BrokerPluginSupport {

    private final static Logger log = LoggerFactory.getLogger(CustomBrokerPlugin.class);

    @Override
    public void postProcessDispatch(MessageDispatch messageDispatch) {
        super.postProcessDispatch(messageDispatch);
        final Message message = messageDispatch.getMessage();
        if (message instanceof ActiveMQTextMessage) {
            try {
                log.info("Dispatched message with text {}", ((ActiveMQTextMessage) message).getText());
            } catch (final Exception e) {
                log.trace("Unable to get text from message {}", message, e);
            }
        }
    }

}