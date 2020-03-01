package com.example.user.controller;

import com.example.user.pojo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author Sean
 * 2020/02/29
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/get/{id}")
    public UserVO getUser(@PathVariable("id") Long id) {
        ServiceInstance eureka_user = discoveryClient.getInstances("EUREKA-USER").get(0);
        System.out.println("【" + eureka_user.getServiceId() + "】:" +
                eureka_user.getHost() + ":" + eureka_user.getPort());
        UserVO userVO = new UserVO();
        userVO.setUserId(id);
        userVO.setUserName("userName_" + id);
        userVO.setLeavel(id.intValue());
        return userVO;
    }

    @GetMapping("/timeout")
    public String timeout(){
        long ms = (long) (3000L*Math.random());
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "熔断测试";
    }

}
