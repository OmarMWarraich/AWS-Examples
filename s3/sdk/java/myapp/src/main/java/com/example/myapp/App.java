package com.example.myapp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.util.concurrent.CompletableFuture;

/**
 * Lambda function entry point. You can change to use other pojo type or implement
 * a different RequestHandler.
 *
 * @see <a href=https://docs.aws.amazon.com/lambda/latest/dg/java-handler.html>Lambda Java Handler</a> for more information
 */
public class App implements RequestHandler<Object, Object> {
    private final S3AsyncClient s3Client;

    public App() {
        // Initialize the SDK client outside of the handler method so that it can be reused for subsequent invocations.
        // It is initialized when the class is loaded.
        s3Client = DependencyFactory.s3Client();
        // Consider invoking a simple api here to pre-warm up the application, eg: dynamodb#listTables
    }

    @Override
    public Object handleRequest(final Object input, final Context context) {
        String bucketName = "my-new-bucket-owa-786" + System.currentTimeMillis();
        createBucket(bucketName);
        return "Bucket creation initiated: " + bucketName;
    }

    private void createBucket(String bucketName) {
        CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                .bucket(bucketName)
                .build();

        CompletableFuture<CreateBucketResponse> futureResponse = s3Client.createBucket(createBucketRequest);

        futureResponse.whenComplete((response, exception) -> {
            if (response != null) {
                System.out.println("Bucket created: " + response.location());
            } else {
                exception.printStackTrace();
                if (exception instanceof S3Exception) {
                    S3Exception s3Exception = (S3Exception) exception;
                    System.err.println("Error creating bucket: " + s3Exception.awsErrorDetails().errorMessage());
                }
            }
        });
    }

}
