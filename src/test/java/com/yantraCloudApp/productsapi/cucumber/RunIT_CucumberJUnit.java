package com.yantraCloudApp.productsapi.cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(
        features = "classpath:features/Products.feature",
        glue = "com.yantraCloudApp.productsapi.cucumber.stepdefs",
        plugin = {"pretty","summary",
                "html:target/cucumber-reports.html",
                "json:target/json_result.json"
        },
        tags = "",
        dryRun = false
)
@RunWith(Cucumber.class)
public class RunIT_CucumberJUnit  {
}
