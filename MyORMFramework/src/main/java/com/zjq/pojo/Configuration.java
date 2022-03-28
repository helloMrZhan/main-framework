package com.zjq.pojo;

import lombok.Data;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 映射语句实体
 *
 * @author zjq
 * @date 2022/3/15
 */
@Data
public class Configuration {

    private DataSource dataSource;

    /**
     * key: statementId  value:封装好的mappedStatement对象
     */
    Map<String, MappedStatement> mappedStatementMap = new HashMap<>();
}