package com.ray.schedule;

import com.ray.search.SolrSearch;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Ray on 2017/3/31.
 */


//注意 以下两个注解，启动定时任务

@Component
@EnableScheduling
public class Schedule {
    @Scheduled(cron = "0 0 15 * * *")
    public void out(){
        //每天15:00执行
        SolrSearch solrSearch = new SolrSearch();
        solrSearch.solrIndex();
    }
}
