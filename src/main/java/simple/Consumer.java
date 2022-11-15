package simple;

import client.AwsSqsClient;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;

import java.util.List;

public class Consumer {

    public static void main(String[] args) {
        try{
            AmazonSQS sqs = AwsSqsClient.getAwsSqsClient();
            while(true){
                List<Message> messages = sqs.receiveMessage(AwsSqsClient.getQueueUrl()).getMessages();
                if(messages.size() == 0){
                    System.out.println("thread sleeping");
                    Thread.sleep(5000);
                }
                else{
                    messages.forEach(x -> {
                        System.out.println("message >> "+x.getBody());
                        DeleteMessageRequest dr = new DeleteMessageRequest();
                        dr.setQueueUrl(AwsSqsClient.getQueueUrl());
                        dr.setReceiptHandle(x.getReceiptHandle());
                        sqs.deleteMessage(dr);
                    });
                }
            }
        }catch (Exception e){
            System.out.println("error - " +e.getMessage());
        }
    }
}
