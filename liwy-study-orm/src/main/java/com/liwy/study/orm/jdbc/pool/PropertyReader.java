package com.liwy.study.orm.jdbc.pool;

import java.util.Properties;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/1/16 16:17 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class PropertyReader {
    private static Properties ps;

    static {
        ps = new Properties();
        try {
            ps.load(PropertyReader.class.getResourceAsStream("/config-jdbc.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return (String) ps.get(key);
    }
}
