package com.liwy.study;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Hello world!
 *
 */
public class QueueSender
{
    public static void main( String[] args ) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://192.168.2.10:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("my_queue");
        MessageProducer messageProducer = session.createProducer(destination);


        for (int i = 0; i < 5; i++) {
            TextMessage textMessage = session.createTextMessage("发送的消息"+i);
            System.out.println("发送消息:"+textMessage.getText());
            messageProducer.send(textMessage);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
