package cn.yiueil.convert.impl;

import cn.yiueil.convert.Converter;

/**
 * Author:YIueil
 * Date:2022/4/15 15:12
 * Description: String转换器
 */
public class StringConverter implements Converter<String> {
    @Override
    public String convert(Object obj, String defaultValue) {
        return obj == null ? defaultValue : obj.toString();
    }
}
