package top.codeease.stockwatch.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author by: 神秘的鱼仔
 * @ClassName: DateUtil
 * @Description: 时间工具类
 * @Date: 2024/7/12 下午11:48
 */
public class DateUtil {

    /**
     * 获取当前的时间
     * @return
     */
    public static String getNowTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
