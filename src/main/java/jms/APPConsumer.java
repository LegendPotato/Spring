package jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class APPConsumer {

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

        //6.消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //7.接受消息，监听器
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接受消息"+ textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });



        //connection.close();

    }
}
