package com.liwy.study.mq.rabbitmq.routing;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * <b>名称：</b> 消费者<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/3/27 14:41 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class Consumer1 {
    private final static String EXCHANGE = "rout"; // 交换机的名字
    private final static String QUEUE = "rout_1"; // 队列名称
    private final static String ROUTING_KEY = "goods_save"; //路由key

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.105.131.42");
        factory.setPort(5672);
        factory.setUsername("liwy");
        factory.setPassword("123456");
        factory.setVirtualHost("/liwy");
        Connection connection = factory.newConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        // 创建队列，如果存在什么都不做，如果不存在才创建
        // 参数1：队列名称
        // 参数2：是否持久化队列，我们的队列模式是在内存中的，如果rabbitmq重启会丢失，如果我们设置为true，则会保存到erlang自带的数据库中，重启后会重新读取到内存
        // 参数3：是否排外，有两个作用，第一个当我们的连接关闭后是否会自动删除队列，作用二，是否私有当前队列，如果私有了，其他通道不可以访问该队列
        // 参数4：是否自动删除
        // 参数5：我们的一些其他参数
        channel.queueDeclare(QUEUE, false, false, false, null);

        // 绑定队列到交换机上
        channel.queueBind(QUEUE, EXCHANGE, ROUTING_KEY);

        // 告诉服务器，在我没有确认当前消息完成之前不要再发给我新的消息
        channel.basicQos(1);

        // 定义消费者对象
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("消费者1接收到消息：" + message);

                // 模拟消费时间较长
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 手动确认收到消息
                // 参数2：如果为false表示确认收到消息，如果为true表示拒绝接收消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        // 接收内容
        // 参数1：队列名称
        // 参数2：是否自动确认
        // 参数3：消费者对象
        channel.basicConsume(QUEUE, false, consumer);
    }
}
