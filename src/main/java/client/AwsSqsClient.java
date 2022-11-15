package client;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class AwsSqsClient {

    public static AmazonSQS sqs = null;
    public static Properties properties = null;

    public static AmazonSQS getAwsSqsClient() throws IOException {
        if(properties == null)
            loadProperties();
        if(sqs == null) {
            sqs = AmazonSQSClientBuilder.standard().withCredentials(
                    getAwsCredentialProvider()
            ).build();
            sqs.createQueue(properties.getProperty("queueName"));
        }
        return sqs;
    }

    public static void loadProperties() throws IOException {
        properties = new Properties();
        properties.load(Files.newInputStream(Paths.get("src/main/resources/config.properties")));
    }

    public static AWSCredentialsProvider getAwsCredentialProvider() throws IOException {
        if(properties == null)
            loadProperties();
        return new AWSCredentialsProvider() {
            public AWSCredentials getCredentials() {
                return new BasicAWSCredentials(properties.getProperty("awsKey"), properties.getProperty("awsSecret"));
            }

            public void refresh() {
            }
        };
    }

    public static String getQueueUrl(){
        return sqs.getQueueUrl(properties.getProperty("queueName")).getQueueUrl();
    }


}
