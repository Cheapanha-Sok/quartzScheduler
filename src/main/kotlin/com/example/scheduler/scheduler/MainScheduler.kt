package com.example.scheduler.scheduler

import com.example.scheduler.info.TimerInfo
import com.example.scheduler.utillities.anotation.Sl4JLogger
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.quartz.*
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.stereotype.Service
import com.example.scheduler.utillities.anotation.Sl4JLogger.Companion.log
import com.example.scheduler.utillities.quartz.Timer


@Service
@Sl4JLogger
class MainScheduler (
    private val scheduler: Scheduler,
    private val timer: Timer
){

    @PostConstruct
    fun startSchedule(){
        try{
            scheduler.start()
        }catch (e : SchedulerException){
            log.error(e.message)
        }

    }
    fun scheduleJobInterval(className: Class<out Job>, info: TimerInfo?) {
        try {
            val jobDetail: JobDetail = timer.buildJobDetail(className, info!!)
            val triggerDetail: Trigger = timer.buildTrigger(className,info)
            scheduler.scheduleJob(jobDetail, triggerDetail)
        } catch (e: SchedulerException) {
            log.error(e.message)
        }
    }
    fun scheduleJobCronExpression(className: Class<out Job>, cronExpression : String) {
        try {
            val jobDetail: JobDetail = timer.buildJobDetail(className)
            val triggerDetail: Trigger = timer.buildTriggerCronExpression(className,cronExpression)
            scheduler.scheduleJob(jobDetail, triggerDetail)
        } catch (e: SchedulerException) {
            log.error(e.message)
        }
    }

    @PreDestroy
    fun closeScheduler() {
        try {
            scheduler.shutdown()
        } catch (e: SchedulerException) {
            log.error(e.message)
        }
    }
    fun resumeAll() {
        try {
            scheduler.resumeAll()
        } catch (e: SchedulerException) {
            log.error(e.message)
        }
    }

    fun resumeSpecificJob(groupName: String?, jobName: String?) {
        try {
            val jobKey = JobKey(jobName, groupName)
            scheduler.resumeJob(jobKey)
        } catch (e: SchedulerException) {
            log.error(e.message)
        }
    }

    fun deleteJob(jobName: String?, groupName: String?) {
        val jobkey = JobKey(jobName, groupName)
        try {
            scheduler.deleteJob(jobkey)
        } catch (e: SchedulerException) {
            log.error(e.message)
        }
    }

    fun deleteAllJobs() {
        try {
            val jobKeys = scheduler.getJobKeys(GroupMatcher.anyGroup())
            val jobKeysList: List<JobKey> = ArrayList(jobKeys)
            scheduler.deleteJobs(jobKeysList)
        } catch (e: SchedulerException) {
            log.error(e.message)
        }
    }
}