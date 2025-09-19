package com.quizmate.common.core.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * <h3>quizmate-backend</h3>
 * <p>表格分页数据对象</p>
 *
 * @author moru
 * @since 2025-09-17 14:29
 */
@Setter
@Getter
public class TableDataRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 列表数据
     */
    private List<?> rows;

    /**
     * 消息状态码
     */
    private int code;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 表格数据对象
     */
    public TableDataRequest() {
    }

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public TableDataRequest(List<?> list, long total) {
        this.rows = list;
        this.total = total;
    }
}
