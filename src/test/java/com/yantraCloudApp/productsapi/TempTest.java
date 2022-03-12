package com.yantraCloudApp.productsapi;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;



public class TempTest {

    @Test
    public void test(){
        final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));
        mongoDBContainer.withExposedPorts(8081).start();
        System.out.println("gd");
        mongoDBContainer.stop();
    }
}
