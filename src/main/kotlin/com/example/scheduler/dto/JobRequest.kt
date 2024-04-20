package com.example.scheduler.dto

import java.time.ZoneId

data class JobRequest(
    var jobName :String,
    var jobClass : String,
    var timeZone : ZoneId,
    var description :String?,
    var cronExpression :String?=null,
)

data class UpdateJob(
    var description :String?,
    var cronExpression: String?,
)