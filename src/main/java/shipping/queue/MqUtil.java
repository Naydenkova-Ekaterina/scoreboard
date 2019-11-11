package shipping.queue;

import com.rabbitmq.client.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class MqUtil {

    private static final Logger logger = Logger.getLogger(MqUtil.class);
    Channel channel;
    Connection connection;
    private Listener listener = Listener.getListener();

    public void start() throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare("shippingProject", false, false, false, null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                String message = new String(body, StandardCharsets.UTF_8);
                logger.info("Received message = \"" + message + "\"");
                listener.dataShouldBeUpdated();
            }
        };
        channel.basicConsume("shippingProject", true, consumer);
    }

    public void stop() throws IOException, TimeoutException {
        connection.close();
        channel.close();
    }

}
