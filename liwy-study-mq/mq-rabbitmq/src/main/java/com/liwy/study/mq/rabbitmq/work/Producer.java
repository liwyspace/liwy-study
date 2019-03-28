package com.liwy.study.mq.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * <b>名称：</b> Work模式 生产者<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/3/27 14:20 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class Producer {

    private final static String QUEUE = "work"; // 队列名称

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

        for(int i=0;i<20;i++) {
            // 发送内容
            // 参数1：交换器名称
            // 参数2：队列名称
            // 参数3：基础属性
            // 参数4：消息内容
            channel.basicPublish("", QUEUE, null, ("发送的内容"+i).getBytes());
        }

        // 关闭通道和连接
        channel.close();
        connection.close();

    }
}
