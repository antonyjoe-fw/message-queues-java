package simple;

import client.AwsSqsClient;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class Producer {

    public static void main(String[] args) {
        try {
            AmazonSQS sqs = AwsSqsClient.getAwsSqsClient();
            for(int i = 1; i < 6; i++) {
                SendMessageRequest message = new SendMessageRequest()
                        .withQueueUrl(AwsSqsClient.getQueueUrl())
                        .withMessageBody("hello world "+i);
                sqs.sendMessage(message);
                System.out.println("sent message  - "+i);
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            System.out.println("error - " + e.getMessage());
        }
    }

}
