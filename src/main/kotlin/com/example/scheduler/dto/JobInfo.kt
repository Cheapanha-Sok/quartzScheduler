package com.example.scheduler.dto

data class JobInfo(
    val jobName: String,
    val jobClass: String,
    val groupName :String,
    val description: String?,
    val cronExpression: String?,
    val status: String,
)