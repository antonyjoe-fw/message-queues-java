package simple;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class Producer {

    public static void main(String[] args) {
        try {
            AmazonSQS sqs = AwsSqsClient.getAwsSqsClient();
        } catch (Exception e) {
            System.out.println("error - " + e.getMessage());
        }
    }

}
