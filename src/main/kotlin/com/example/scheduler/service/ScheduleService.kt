package com.example.scheduler.service

import com.example.scheduler.base.response.MessageResponse
import com.example.scheduler.base.response.ObjectResponse
import com.example.scheduler.dto.JobInfo
import com.example.scheduler.dto.JobRequest
import com.example.scheduler.dto.UpdateJob
import org.quartz.JobDetail

interface ScheduleService {
    fun index() : ObjectResponse<List<JobInfo>>
    fun show(jobName :String , groupName :String): ObjectResponse<JobDetail>
    fun createJob(req : JobRequest) : MessageResponse
    fun deleteAllJob() : MessageResponse
    fun deleteJob(jobName : String , groupName :String) : MessageResponse
    fun resumeAllJob() : MessageResponse
    fun resumeJob(jobName :String , groupName : String) : MessageResponse
    fun pauseAllJob() : MessageResponse
    fun pauseJob(jobName :String , groupName: String) : MessageResponse
    fun updateJob(groupName: String , jobName: String , updatedJob: UpdateJob) : MessageResponse
}