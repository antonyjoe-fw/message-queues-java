package simple;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;

import java.util.List;

public class Consumer {

    public static void main(String[] args) {
        try{
            AmazonSQS sqs = AwsSqsClient.getAwsSqsClient();

        }catch (Exception e){
            System.out.println("error - " +e.getMessage());
        }
    }
}
