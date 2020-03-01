package com.example.product.configs;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Feign配置
 *
 * @author Sean
 * 2020/02/29
 */
@Configuration
public class FeignConfig {

    @Bean
    public Retryer feignRetry(){
        return new Retryer.Default(100,SECONDS.toMillis(1), 5);
    }
}
