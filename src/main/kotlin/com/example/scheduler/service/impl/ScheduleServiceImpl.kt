package com.example.scheduler.service.impl

import com.example.scheduler.base.response.MessageResponse
import com.example.scheduler.base.response.ObjectResponse
import com.example.scheduler.dto.JobInfo
import com.example.scheduler.dto.JobRequest
import com.example.scheduler.dto.UpdateJob
import com.example.scheduler.exception.InvalidDateException
import com.example.scheduler.exception.NotFoundException
import com.example.scheduler.service.ScheduleService
import com.example.scheduler.utillities.anotation.Sl4JLogger
import com.example.scheduler.utillities.anotation.Sl4JLogger.Companion.log
import org.quartz.*
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.*


@Service
@Sl4JLogger
class ScheduleServiceImpl(
    private val scheduler: Scheduler
) : ScheduleService {

    override fun index(): ObjectResponse<List<JobInfo>> {
        val jobInfos = mutableListOf<JobInfo>()
        val jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup())
        for (jobKey in jobKeys) {
            val jobDetail = scheduler.getJobDetail(jobKey)
            val trigger = scheduler.getTriggersOfJob(jobKey).firstOrNull()

            val jobInfo = JobInfo(
                jobName = jobDetail.key.name,
                jobClass = jobDetail.jobClass.name,
                description = jobDetail.description,
                cronExpression = trigger?.let {
                    if (it is CronTrigger) it.cronExpression else "Simple Trigger"
                } ?: "No Trigger",
                groupName = jobDetail.key.group,
                status = scheduler.getTriggerState(trigger!!.key).name
            )
            jobInfos.add(jobInfo)
        }
        return ObjectResponse(jobInfos)
    }

    override fun show(jobName :String , groupName :String): ObjectResponse<JobDetail> {
        try {
            val jobKey = JobKey(jobName, groupName)
            return ObjectResponse(scheduler.getJobDetail(jobKey))
        } catch (e: SchedulerException) {
            throw java.lang.RuntimeException(e)
        }
    }

    override fun createJob(req: JobRequest): MessageResponse {
        try {
            val dateTime = ZonedDateTime.of(req.dateTime, req.timeZone)
            if (dateTime.isBefore(ZonedDateTime.now())) {
                throw InvalidDateException("Date time must be after the current time")
            }
            val jobClass = isClassExist(req.jobClass)
            log.info("date start${dateTime}")
            val jobDetail: JobDetail = buildJobDetail(jobClass, req)
            val trigger: Trigger = if (req.cronExpression != null) {
                log.info("cron trigger")
                buildCronTrigger(jobDetail, dateTime, req.cronExpression!!)
            } else {
                log.info("simple trigger")
                buildTrigger(jobDetail, dateTime)
            }
            scheduler.scheduleJob(jobDetail, trigger)
        } catch (e: SchedulerException) {
            throw RuntimeException("Something when wrong", e)
        }
        return MessageResponse()
    }

    override fun deleteAllJob(): MessageResponse {
        try {
            val jobKeys = scheduler.getJobKeys(GroupMatcher.anyGroup())
            val jobKeysList: List<JobKey> = ArrayList(jobKeys)
            scheduler.deleteJobs(jobKeysList)
            return MessageResponse()
        } catch (e: SchedulerException) {
            throw RuntimeException("Something when wrong", e.cause)
        }
    }

    override fun deleteJob(jobName: String, groupName: String): MessageResponse {
        val jobKey = JobKey(jobName, groupName)
        try {
            scheduler.deleteJob(jobKey)
            return MessageResponse()
        } catch (e: SchedulerException) {
            throw RuntimeException("Something when wrong", e.cause)
        }
    }

    override fun resumeAllJob(): MessageResponse {
        try {
            scheduler.resumeAll()
            return MessageResponse()
        } catch (e: SchedulerException) {
            throw RuntimeException("Something when wrong", e.cause)
        }
    }

    override fun resumeJob(jobName: String, groupName: String): MessageResponse {
        try {
            val jobKey = JobKey(jobName, groupName)
            scheduler.resumeJob(jobKey)
            return MessageResponse()
        } catch (e: SchedulerException) {
            throw RuntimeException("Something when wrong", e.cause)
        }
    }

    override fun pauseAllJob(): MessageResponse {
        try {
            scheduler.pauseAll()
            return MessageResponse()
        } catch (e: SchedulerException) {
            throw RuntimeException("Something when wrong", e.cause)
        }
    }

    override fun pauseJob(jobName: String, groupName: String): MessageResponse {
        try {
            val jobKey = JobKey(jobName, groupName)
            scheduler.pauseJob(jobKey)
            return MessageResponse()
        } catch (e: SchedulerException) {
            throw RuntimeException("Something when wrong", e.cause)
        }
    }

    override fun updateJob(groupName: String, jobName: String, updatedJob: UpdateJob): MessageResponse {
        try {
            val jobKey = JobKey(jobName, groupName)
            val existingJobDetail = scheduler.getJobDetail(jobKey) ?: throw RuntimeException("Job not found")
            // Create a JobBuilder based on the existing job detail
            var jobBuilder = existingJobDetail.jobBuilder
            // Update for description if the description is not null
            updatedJob.description?.let {
                jobBuilder = jobBuilder.withDescription(it)
            }
            // Update for cronExpression if the cronExpression is not null
            updatedJob.cronExpression?.let {
                val existingTriggers = scheduler.getTriggersOfJob(jobKey)
                if (existingTriggers.isNotEmpty()) {
                    val existingTrigger = existingTriggers.first()
                    if (existingTrigger is CronTrigger) {
                        // Create a new trigger with the updated cron expression
                        val newTrigger = existingTrigger.triggerBuilder.withSchedule(
                            CronScheduleBuilder.cronSchedule(it).withMisfireHandlingInstructionFireAndProceed()
                        ).build()
                        // Reschedule the job with the new trigger
                        scheduler.rescheduleJob(existingTrigger.key, newTrigger)
                    } else {
                        throw RuntimeException("Job does not have a cron trigger")
                    }
                }
            }
            // Update the job in the scheduler with the new JobDetail
            scheduler.addJob(jobBuilder.build(), true)
            return MessageResponse()
        } catch (e: SchedulerException) {
            throw RuntimeException("Something went wrong during job update", e)
        }
    }

    private fun isClassExist(className :String) : Class<out Job>{
        try {
            val fullyQualifiedClassName = "com.example.scheduler.job.$className"
            val jobClass = Class.forName(fullyQualifiedClassName) as Class<Job>
            return jobClass
        }catch (e : ClassNotFoundException){
            throw NotFoundException("Class not found with name $className")
        }
    }


    private fun buildJobDetail(jobClass: Class<out Job>, req: JobRequest) =
        JobBuilder.newJob(jobClass)
            .storeDurably()
            .withIdentity(req.jobName, req.jobName)
            .withDescription(req.description)
            .build()

    private fun buildTrigger(jobDetail: JobDetail, startAt: ZonedDateTime) =
        TriggerBuilder.newTrigger()
            .forJob(jobDetail)
            .withIdentity("${jobDetail.key.name}-simple-trigger", jobDetail.key.name)
            .withDescription("${jobDetail.key.name} job desc")
            .startAt(Date.from(startAt.toInstant()))
            .withSchedule(SimpleScheduleBuilder.simpleSchedule())
            .build()

    private fun buildCronTrigger(jobDetail: JobDetail, startAt: ZonedDateTime, cronPattern: String) =
        TriggerBuilder.newTrigger()
            .forJob(jobDetail)
            .withIdentity("${jobDetail.key.name}-cron-trigger", jobDetail.key.name)
            .withDescription("${jobDetail.key.name} job desc")
            .startAt(Date.from(startAt.toInstant()))
            .withSchedule(
                CronScheduleBuilder.cronSchedule(cronPattern)
                    .withMisfireHandlingInstructionFireAndProceed()
            )
            .build()
}