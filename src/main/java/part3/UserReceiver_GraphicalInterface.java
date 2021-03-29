package part3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import shared.CreatedBorder;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class UserReceiver_GraphicalInterface {
    public final static String QUEUE_NAME="commQueue";

    public static void main(String[] args) throws IOException, TimeoutException {
        receive();
    }

    static void receive() throws IOException, TimeoutException {
        JPanel jPanel = new JPanel();
        jPanel.setBorder(new TitledBorder(new EtchedBorder(), "User Messages Consume"));
        jPanel.setBounds(0,0,650,550);
        JFrame jFrame= new JFrame("RabbitMQ MSG consume");
        JTextArea jTextArea=new JTextArea(20,20);
        Font fieldFont = new Font("TimesRoman", Font.BOLD, 15);
        jTextArea.setFont(fieldFont);
        jTextArea.setBorder(BorderFactory.createCompoundBorder(
                new CreatedBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);


        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setBounds(10, 265, 455, 249);

        jPanel.add(jScrollPane);
        jFrame.add(jPanel);
        jFrame.setSize(650,600);
        jFrame.setLayout(null);
        jFrame.setVisible(true);


        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);


        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String receivedMessage = new String(delivery.getBody(),"UTF-8");
            jTextArea.setText(receivedMessage);
            System.out.println(" [x] sent '"+receivedMessage+" '");

        };
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,consumerTag -> {});
    }
}
