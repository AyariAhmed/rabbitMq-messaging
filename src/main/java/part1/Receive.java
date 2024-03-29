package part1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Receive {
    private final static String QUEUE_NAME="hello";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory= new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel= connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        System.out.println(" [*] Waiting for messages. To Exit press CTRL+C");

        DeliverCallback deliverCallback=(consummerTag, delivery) -> {
            String message= new String(delivery.getBody(),"UTF-8");
            System.out.println(" [x] Recieved ' "+message+" '");
        };

        channel.basicConsume(QUEUE_NAME,true, deliverCallback, consumerTag->{});
    }
}
