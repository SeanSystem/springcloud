package com.example.product.service;

import com.example.product.configs.FeignConfig;
import com.example.product.impl.UserServiceFallBack;
import com.example.product.pojo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 获取用户信息服务
 *
 * @author Sean
 * 2020/02/29
 */
@Primary
@FeignClient(value = "eureka-user", fallback = UserServiceFallBack.class, configuration = FeignConfig.class)
public interface UserService {

    @GetMapping("/user/get/{id}")
    UserVO getUser(@PathVariable("id") Long id);

    @GetMapping("/user/timeout")
    String testHys();
}
