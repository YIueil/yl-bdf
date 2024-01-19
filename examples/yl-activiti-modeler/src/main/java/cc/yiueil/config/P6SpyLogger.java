package cc.yiueil.config;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * P6SpyLogger
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/9/4 16:06
 */
public class P6SpyLogger implements MessageFormattingStrategy {

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        StringBuilder sb = new StringBuilder();
        return !"".equals(sql.trim()) ? sb.append(this.format.format(new Date())).append(" | took ").append(elapsed).append("ms | ").append(category).append(" | connection ").append(connectionId).append(sql).append(";").toString() : "";
    }
}
