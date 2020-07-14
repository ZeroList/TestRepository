package com.itsx.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsConsumer {
    public static final String ACTIVEMQ_URL = "tcp://39.108.94.119:61616";
    public static final String QUEUE_NAME = "ItSix";   // 1对1 的队列

    public static void main(String[] args) throws Exception{
        // 1 按照给定的url创建连接工程，这个构造器采用默认的用户名密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 通过连接工厂连接 connection  和 启动
        Connection connection = activeMQConnectionFactory.createConnection();
        //  启动
        connection.start();
        // 3 创建回话  session
        // 两个参数，第一个事务， 第二个签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4 创建目的地 （两种 ： 队列/主题   这里用队列）
        Queue queue = session.createQueue(QUEUE_NAME);
        // 5 创建消息的消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);
        //while(true){
        //    // 这里是 TextMessage 是因为消息发送者是 TextMessage ， 接受处理的
        //    // 也应该是这个类型的消息
        //    TextMessage message = (TextMessage)messageConsumer.receive(4000L);
        //    if (null != message){
        //        System.out.println("****消费者的消息："+message.getText());
        //    }else {
        //        break;
        //    }
        //}

        // 通过监听的方式来消费消息
        // 通过异步非阻塞的方式消费消息
        // 通过messageConsumer 的setMessageListener 注册一个监听器，
        // 当有消息发送来时，系统自动调用MessageListener 的 onMessage 方法处理消息
        messageConsumer.setMessageListener(new MessageListener() {   // 可以用监听器替换之前的同步receive 方法
            public void onMessage(Message message)  {
                if (null != message  && message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage)message;
                    try {
                        System.out.println("****消费者的消息："+textMessage.getText());
                    }catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        System.in.read();//让控制台等待一会，不然消费者还未消费消息 下面三个连接就被关闭了。
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
