package com.example.user.pojo;

import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author Sean
 * 2020/02/29
 */
public class UserVO implements Serializable {

    private static final long serialVersionUID = -2999352060067363215L;

    private Long userId;

    private String userName;

    private Integer leavel;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getLeavel() {
        return leavel;
    }

    public void setLeavel(Integer leavel) {
        this.leavel = leavel;
    }
}
