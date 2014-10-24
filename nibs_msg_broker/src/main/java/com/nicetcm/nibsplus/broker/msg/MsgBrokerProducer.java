package com.nicetcm.nibsplus.broker.msg;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;




import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.*;

public class MsgBrokerProducer {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerProducer.class);

    public static final ConcurrentMap<String, MsgBrokerProducer> producers = new ConcurrentHashMap<String, MsgBrokerProducer>();

    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;

    private String broker;
    private String queue;

    public static void putDataToPrd(MsgParser msgPsr) throws Exception {

        MsgBrokerProducer prd = null;
        if( msgPsr.getString("CM.org_cd").equals(MsgBrokerConst.NICE_CODE) ) {
            prd = MsgBrokerProducer.producers.get( String.format("ATMS.%s.%s.Q",
                                                                 msgPsr.getString("CM.msg_type"),
                                                                 msgPsr.getString("CM.work_type")) );
        }
        else {
            prd = MsgBrokerProducer.producers.get( String.format("ATMS.%s.H.Q", msgPsr.getString("CM.org_cd")) );
        }
        BytesMessage respData = prd.getBytesMessage();

        byte[] read = new byte[msgPsr.getMessage().limit()];
        msgPsr.getMessage().get(read);
        logger.warn("O-MSG : [{}],[{}]", read.length, new String(read));
        respData.writeBytes(read);
        prd.produce( respData );

    }

    public static void putDataToPrd(MsgParser msgPsr, String orgCd) throws Exception {

        msgPsr.setString( "CM.org_cd", orgCd );
        putDataToPrd( msgPsr );

    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public MsgBrokerProducer(String broker, String queue) throws JMSException {
        this.broker = broker;
        this.queue = queue;

        init();
    }

    public void init() throws JMSException {
        factory = new ActiveMQConnectionFactory(broker);

        connection = factory.createConnection();
        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        destination = session.createQueue(queue);

        producer = session.createProducer(destination);
    }

    public BytesMessage getBytesMessage() throws JMSException {
        return session.createBytesMessage();
    }

    public MapMessage getMapMessage() throws JMSException {
        return session.createMapMessage();
    }

    public Message getMessage() throws JMSException {
        return session.createMessage();
    }

    public ObjectMessage getObjectMessage() throws JMSException {
        return session.createObjectMessage();
    }

    public StreamMessage getStreamMessage() throws JMSException {
        return session.createStreamMessage();
    }

    public TextMessage getTextMessage() throws JMSException {
        return session.createTextMessage();
    }

    public void produce(Message message) throws JMSException {
        producer.send(message);
    }

    public void close() throws JMSException {
        producer.close();
        session.close();
        connection.close();
    }

    public Connection getConnection() {
        return connection;
    }

    public Session getSession() {
        return session;
    }

    public MessageProducer getProducer() {
        return producer;
    }

}