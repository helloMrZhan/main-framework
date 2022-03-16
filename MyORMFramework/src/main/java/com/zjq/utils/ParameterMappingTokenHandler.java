package com.zjq.utils;

import com.zjq.mapping.ParameterMapping;
import java.util.ArrayList;
import java.util.List;

/**
 * 参数处理器
 * @author zjq
 * @date 2022/3/16
 */
public class ParameterMappingTokenHandler implements TokenHandler {
    private List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();


    /**
     *
     * @param content context是参数名称 #{id} #{username}
     * @return
     */
    @Override
    public String handleToken(String content) {
        parameterMappings.add(buildParameterMapping(content));
        return "?";
    }

    private ParameterMapping buildParameterMapping(String content) {
        ParameterMapping parameterMapping = new ParameterMapping(content);
        return parameterMapping;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }

}
