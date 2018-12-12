package com.blackie.plugins;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.internal.util.StringUtility;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by BlackieZ 365227949@qq.com
 */
public class OverwriteXmlPlugin extends PluginAdapter {
    public static final String IS_MERGEABLE = "isMergeable";
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        String isMergeable = properties.getProperty(IS_MERGEABLE);
        try {
            Field field = sqlMap.getClass().getDeclaredField(IS_MERGEABLE);
            field.setAccessible(true);
            field.setBoolean(sqlMap, StringUtility.isTrue(isMergeable) ? true : false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
