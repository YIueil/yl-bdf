package cc.yiueil.convert.impl.base;

import cc.yiueil.convert.Converter;
import cc.yiueil.util.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Author:YIueil
 * Date:2022/4/15 15:12
 * Description: Date转换器
 * 依赖项：
 * @see DateUtils
 */
public class DateConverter implements Converter<Date> {
    @Override
    public Date convert(Object o, Date defaultValue) {
        if (o == null) {
            return defaultValue;
        }
        if (o instanceof Long) { // 时间戳的转换
            return new Date((Long) o);
        }
        if (o instanceof CharSequence) { // 字符串的转换规则
            return DateUtils.str2Date(o.toString());
        }
        if (o instanceof LocalDate) {
            return DateUtils.localDate2Date((LocalDate) o);
        }
        if (o instanceof LocalDateTime) {
            return DateUtils.localDateTime2Date((LocalDateTime) o);
        }
        return defaultValue;
    }
}
