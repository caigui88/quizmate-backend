package com.quizmate.business.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <h3>quizmate-backend</h3>
 * <p>用户信息请求类</p>
 *
 * @author moru
 * @since 2025-09-15 23:30
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userAvatar;
    /**
     * 用户简介
     */
    private String userProfile;
    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;
    /**
     * 创建时间
     */
    private Date createTime;
}
