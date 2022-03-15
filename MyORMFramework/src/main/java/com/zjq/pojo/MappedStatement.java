package com.zjq.pojo;

import lombok.Data;

/**
 * 数据源和映射语句集合实体
 * @author zjq
 * @date 2022/3/15
 */
@Data
public final class MappedStatement {

    private String id;
    /**
     * sql语句
     */
    private String sql;
    /**
     * 参数值类型
     */
    private String parameterType;
    /**
     * 返回值类型
     */
    private String resultType;
}
