package cn.com.fqz.common;

/**
 * 获取时间和日期相关的工具类
 *
 * @author 众里阑珊
 */
public class DateUtils {

    /**
     * 线程进行暂停的时间
     *
     * @param interval
     */
    public static void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }
}
