package com.yantraCloudApp.productsapi.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("new_config")
public class NewConfigForTrialOnly {

    @Getter @Setter
    private String token;

}
