package com.liwy.study;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by liwy on 2017/5/5.
 */
public class QueueReceiver {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://192.168.2.10:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("my_queue");
        MessageConsumer messageConsumer = session.createConsumer(destination);

        int i=0;
        while (i<3){
            TextMessage textMessage = (TextMessage) messageConsumer.receive(1000);
            session.commit();
            System.out.println("收到消息："+textMessage.getText());
            i++;
        }

        session.close();
        connection.close();

    }
}
