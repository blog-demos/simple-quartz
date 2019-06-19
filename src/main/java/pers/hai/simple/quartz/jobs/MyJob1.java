package pers.hai.simple.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * TODO
 * <p>
 * Create Time: 2019-06-19 15:22
 * Last Modify: 2019-06-19
 *
 * @author Q-WHai
 * @see <a href="https://github.com/qwhai">https://github.com/qwhai</a>
 */
public class MyJob1 implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("定时器任务执行" + new Date(System.currentTimeMillis()));
        JobDataMap map = jobExecutionContext.getMergedJobDataMap();
        System.out.println("参数值"+map.get("uname"));
    }
}
