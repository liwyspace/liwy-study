package com.liwy.study.mq.rabbitmq.publish;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * <b>名称：</b> 发布订阅模式 生产者<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/3/27 14:20 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class Producer {
    private final static String EXCHANGE = "publish"; // 交换机的名字

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

        // 创建交换机
        // 参数1：交换器名称
        // 参数2：交换机类型，fanout为发布订阅模式
        channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.FANOUT);

        // 发布消息，发布订阅模式，因为消息是先到交换机中的，而交换机没有保存功能，如果没有消费者，则消息会丢失
        // 参数1：交换器名称
        // 参数2：路由key
        // 参数3：属性
        // 参数4：发布的消息
        channel.basicPublish(EXCHANGE, "", null, "发布的消息".getBytes());

        // 关闭通道和连接
        channel.close();
        connection.close();

    }
}
