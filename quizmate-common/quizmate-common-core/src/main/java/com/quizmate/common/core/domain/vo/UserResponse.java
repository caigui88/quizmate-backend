package com.quizmate.common.core.domain.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * <h3>quizmate-backend</h3>
 * <p>用户信息视图响应</p>
 *
 * @author moru
 * @since 2025-09-17 14:31
 */
@Data
public class UserResponse implements Serializable {

    @Serial
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
    /**
     * 签到数
     */
    private Integer signInCount;
    /**
     * 通过题目数量
     */
    private Integer questionPassCount;
}
