package com.example.scheduler.dto

import org.quartz.Job
import java.time.LocalDateTime
import java.time.ZoneId

data class JobRequest(
    var jobName :String,
    var jobClass : String,
    var dateTime: LocalDateTime,
    var timeZone : ZoneId,
    var description :String?,
    var cronExpression :String?=null,
)

data class UpdateJob(
    var description :String?,
    var cronExpression: String?,
)