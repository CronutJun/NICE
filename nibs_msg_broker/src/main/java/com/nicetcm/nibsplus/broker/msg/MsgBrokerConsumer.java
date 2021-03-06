package com.nicetcm.nibsplus.broker.msg;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.util.ActiveMQ;

public class MsgBrokerConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerConsumer.class);

    public static final ConcurrentMap<String, MsgBrokerConsumer> consumers = new ConcurrentHashMap<String, MsgBrokerConsumer>();

    public static final ConcurrentMap<String, ActiveMQ> req = new ConcurrentHashMap<String, ActiveMQ>();

    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageConsumer consumer;

    private String broker;
    private String queue;
    private boolean exclusive;
    private int prefetchSize;

    private MsgBrokerListener listener;

    public static void putDataToCon(MsgParser msgPsr, String queueName) throws Exception {

        ActiveMQ mq = null;

        String qName = "";
        if( queueName != null && queueName.length() > 0 )
            qName = queueName;
        else
            qName = String.format("%s.%s", msgPsr.getString("CM.msg_type"), msgPsr.getString("CM.work_type"));

        mq = req.get( qName );
        if( mq == null ) {
            mq = new ActiveMQ(MsgCommon.msgProps.getProperty("consumer.host"), qName, null);
            req.put(qName, mq);
        }
        BytesMessage reqData = mq.getBytesMessage();

        byte[] read = new byte[msgPsr.getMessage().limit()];
        msgPsr.getMessage().get(read);
        logger.error("R-MSG : [{}],[{}]", read.length, new String(read));
        reqData.writeBytes(read);
        mq.produce( reqData );

    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public void setListener(MsgBrokerListener listener) {
        this.listener = listener;
    }

    public MsgBrokerConsumer(String broker, String queue, boolean exclusive, int prefetchSize, MsgBrokerListener listener) throws JMSException {

        this.broker = broker;
        this.queue = queue;
        this.listener = listener;
        this.exclusive = exclusive;
        this.prefetchSize = prefetchSize;
        init();
    }

    public void init() throws JMSException {

        factory = new ActiveMQConnectionFactory(broker);

        connection = factory.createConnection();
        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        if( prefetchSize > 0 )
            destination = session.createQueue(String.format("%s?consumer.exclusive=%s&consumer.prefetchSize=%d", queue, exclusive, prefetchSize));
        else
            destination = session.createQueue(String.format("%s?consumer.exclusive=%s", queue, exclusive));

        listener.getBlockingWorkGroup().threadStart();

        consumer = session.createConsumer(destination);
        consumer.setMessageListener(listener);
    }

    public void close() throws JMSException {
        connection.stop();
        consumer.setMessageListener(null);
        consumer.close();
        listener.getBlockingWorkGroup().threadStop();
        session.close();
        connection.close();
    }

    public Connection getConnection() {
        return connection;
    }

    public Session getSession() {
        return session;
    }

    public MessageConsumer getConsumer() {
        return consumer;
    }

    public MsgBrokerListener getListener() {
        return listener;
    }

}