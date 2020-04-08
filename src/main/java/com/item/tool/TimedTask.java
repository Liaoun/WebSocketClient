package com.item.tool;

import com.item.configure.PA;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务
 */
public class TimedTask {

    /**
     * 统计每日流量
     * param explain(参数说明)
     * 秒（0~59）
     * 分（0~59）
     * 时（0~23）
     * 日（0~31）的某天，需计算
     * 月（0~11）
     * 周几（ 可填1-7 或 SUN/MON/TUE/WED/THU/FRI/SAT
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public static void statisticalTraffic(){
        PA.TODAYPV=0;
        PA.TODAYONLINE=0;
    }
}
