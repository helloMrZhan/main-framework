package com.zjq.mapping;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析后SQL和参数实体
 *
 * @author zjq
 * @date 2022/3/15
 */
@Data
public class BoundSql {

    //解析过后的sql(进行 #{ } 和 ${ } 替换完毕之后的结果sql, 注意每个 #{ }替换完之后就是一个 ?)
    private String sqlText;

    //解析出来的参数
    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

}