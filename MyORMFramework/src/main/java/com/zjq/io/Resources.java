package com.zjq.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 通过类加载器简化对配置文件访问的工具类
 *
 * @author zjq
 * @date 2022/3/15
 */
public class Resources {

    /**
     * 根据配置文件的路径，将配置文件加载成字节输入流，存储在内存中
     *
     * @param path
     * @return
     */
    public static InputStream getResourceAsSteam(String path) throws IOException {
        InputStream in = Resources.class.getClassLoader().getResourceAsStream(path);
        if (in == null) {
            throw new IOException("找不到资源" + path);
        }
        return in;
    }
}
