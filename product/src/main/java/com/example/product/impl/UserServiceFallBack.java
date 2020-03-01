package com.example.product.impl;

import com.example.product.pojo.UserVO;
import com.example.product.service.UserService;
import org.springframework.stereotype.Component;

/**
 * 熔断方法
 *
 * @author Sean
 * 2020/02/29
 */
@Component
public class UserServiceFallBack implements UserService {

    @Override
    public UserVO getUser(Long id) {
        return null;
    }

    @Override
    public String testHys() {
        return "请求失败";
    }
}
