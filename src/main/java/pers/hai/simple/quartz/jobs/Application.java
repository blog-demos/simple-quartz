package pers.hai.simple.quartz.jobs;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * TODO
 * <p>
 * Create Time: 2019-06-19 16:45
 * Last Modify: 2019-06-19
 *
 * @author Q-WHai
 * @see <a href="https://github.com/qwhai">https://github.com/qwhai</a>
 */
public class Application {

    public static void main(String[] args) {
        // 1.创建Scheduler的工厂
        SchedulerFactory factory = new StdSchedulerFactory();
        try {
            // 2.从工厂中获取调度器实例
            Scheduler scheduler = factory.getScheduler();

            // 3.创建JobDetail(作业信息)
            JobDetail job = JobBuilder.newJob(MyJob1.class)
                    .withDescription("this is a test job")
                    .withIdentity("testJob", "testGroup")
                    .build();

            // 向任务传递数据
            JobDataMap jobDataMap = job.getJobDataMap();
            jobDataMap.put("uname", "张三");

            // 任务运行的时间，SimpleSchedle类型触发器有效
            long time = System.currentTimeMillis() + 3 * 1000L;
            Date statTime = new Date(time);

            // 4.创建Trigger
            // 使用SimpleScheduleBuilder或者CronScheduleBuilder
            Trigger t = TriggerBuilder.newTrigger()
                    .withDescription("")
                    .withIdentity("ramTrigger", "ramTriggerGroup")
                    // .startNow()
                    .startAt(statTime)  // 默认当前时间启动
                    //普通计时器
                    //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).withRepeatCount(3)) // 间隔3秒，重复3次
                    //表达式计时器
                    //.withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?")) // 3秒执行一次
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(10)
                            .repeatForever())
                    .build();

            // 5.注册任务和定时器
            scheduler.scheduleJob(job, t);

            // 6.启动 调度器
            scheduler.start();
        } catch (SchedulerException ex) {
            ex.printStackTrace();
        }
    }
}
