package part1;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;


public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args)  {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
        ){
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            String message = "Hello World ! Sent from java rabbitMQ implementation";
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println(" [x] Sent '"+message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
