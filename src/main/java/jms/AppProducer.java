package jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppProducer {

    private  static final String url = "tcp://123.206.83.48:61616";
    private static final String queueName = "queue-test";

    public static void main(String[] args) throws JMSException {

        //1.factory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //2.connection
        Connection connection = connectionFactory.createConnection();
        //3.start
        connection.start();
        //4.session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.destination
        Destination destination = session.createQueue(queueName);
        //6.producer
        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < 100; i++) {
            //7.创建消息
            TextMessage textMessage = session.createTextMessage("test"+i);
            //8.发布消息
            producer.send(textMessage);
            System.out.println("发送消息"+ textMessage.getText());
        }
        connection.close();

    }
}
