package com.zaki.redisson.pojo;

import lombok.Data;

/**
 * @author wangzhen
 * @date 2019-11-26
 */
@Data
public class User {

    private Long id;

    private String username;

    private String email;

    private String password;
    /**
     * 余额
     */
    private String balance;


}
