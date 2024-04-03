package com.example.scheduler.utillities.quartz

import com.example.scheduler.info.TimerInfo
import org.quartz.*
import org.springframework.stereotype.Service
import java.util.Date

@Service
class Timer {

    fun buildJobDetail(className: Class<out Job>, timerInfo: TimerInfo): JobDetail {
        val jobDataMap: JobDataMap = JobDataMap()
        jobDataMap.put(className.simpleName, timerInfo)

        return JobBuilder.newJob(className).withIdentity(className.simpleName, "group1").setJobData(jobDataMap).build()
    }

    fun buildTrigger(className: Class<out Job>, timerInfo: TimerInfo): Trigger {
        var builder: SimpleScheduleBuilder =
            SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(timerInfo.repeatIntervalMs!!)

        builder = if (timerInfo.isRunForever!!) {
            builder.repeatForever()
        } else {
            builder.withRepeatCount(timerInfo.totalFireCount!! - 1)
        }

        return TriggerBuilder
            .newTrigger().withIdentity(className.simpleName, "group1")
            .startAt(Date(System.currentTimeMillis() + timerInfo.initialOffsetMs!!)).withSchedule(builder).build()
    }

    fun buildTriggerCronExpression(className: Class<out Job>, cronExpression: String): Trigger {
        return TriggerBuilder
            .newTrigger()
            .withIdentity(className.simpleName, "group1")
            .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
            .build()
    }
}