package com.example.product.controller;

import com.example.product.pojo.UserVO;
import com.example.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品服务接口
 *
 * @author Sean
 * 2020/02/29
 */
@RestController
public class ProductController {

    @Autowired
    private UserService userService;

    @GetMapping("/feign")
    public String testFeign() {
        for (int i = 0; i < 10; i++) {
            UserVO user = userService.getUser((long) (i + 1));
            System.out.println(user);
        }
        return "测试完成";
    }

    @GetMapping("/hystrix")
    public String testHys() {
        return userService.testHys();
    }
}
