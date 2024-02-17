//package org.dows.ssh;
//
//import cn.hutool.cron.Scheduler;
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.scheduling.Trigger;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class SartApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
//
//
//    @Autowired
//    private SchedulerConfig schedulerConfig;
//    @Resource
//    private TriggerConfig triggerConfig;
//
//    /*该监听器的作用是在容器启动的时候执行定时任务类
//     * */
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        System.out.println("----------------------SartApplicationListener----------------------");
//        log.info("启动spring boot监听定时任务类");
//
//        TriggerKey triggerKey = TriggerKey.triggerKey("trigger1", "group1");
//        try {
//            Scheduler scheduler = schedulerConfig.getScheduler();
//            Trigger trigger = scheduler.getTrigger(triggerKey);
//            if (trigger == null) {
//                trigger = TriggerBuilder.newTrigger()
//                        .withIdentity(triggerKey)
//                        .withSchedule(CronScheduleBuilder.cronSchedule(triggerConfig.getCronExpression()))
//                        .build();
//
//                JobDetail jobDetail = JobBuilder.newJob(ScheduledTaskJob.class)
//                        .withIdentity("PushFileToSftpServerWithFrequency", "PushFileToSftpServerWithFrequency")
//                        .build();
//
//                scheduler.scheduleJob(jobDetail, trigger);
//                scheduler.start();
//
//            }
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//}