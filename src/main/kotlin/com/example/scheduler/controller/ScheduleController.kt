package com.example.scheduler.controller

import com.example.scheduler.base.response.MessageResponse
import com.example.scheduler.base.response.ObjectResponse
import com.example.scheduler.dto.JobInfo
import com.example.scheduler.dto.JobRequest
import com.example.scheduler.dto.UpdateJob
import com.example.scheduler.service.ScheduleService
import org.quartz.JobDetail
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/v1/schedule")


class ScheduleController(private val scheduleService: ScheduleService
) {
    @PostMapping("/createJob")
    fun createScheduleJob(@RequestBody req : JobRequest ) : MessageResponse{
        return scheduleService.createJob(req)
    }
    @GetMapping
    fun getAllScheduleJob() : ObjectResponse<List<JobInfo>>{
        return scheduleService.index()
    }
    @GetMapping("/{groupName}/{jobName}")
    fun getScheduleJob(@PathVariable("groupName") groupName :String,
                       @PathVariable("jobName") jobName :String): ObjectResponse<JobDetail>{
        return scheduleService.show(groupName = groupName , jobName = jobName)
    }
    @PostMapping("resumeAll")
    fun resumeAllJob() : MessageResponse{
        return scheduleService.resumeAllJob()
    }
    @PostMapping("resume/{groupName}/{jobName}")
    fun resumeJob(@PathVariable("groupName") groupName :String,
               @PathVariable("jobName") jobName :String) : MessageResponse{
        return scheduleService.resumeJob(jobName = jobName , groupName = groupName)
    }
    @PostMapping("pauseAll")
    fun pauseAllScheduleJob() : MessageResponse{
        return scheduleService.pauseAllJob()
    }
    @PostMapping("pause/{groupName}/{jobName}")
    fun pauseScheduleJob(@PathVariable("groupName") groupName :String,
                         @PathVariable("jobName") jobName :String) : MessageResponse{
        return scheduleService.pauseJob(jobName = jobName , groupName = groupName)
    }
    @DeleteMapping("deleteAll")
    fun deleteAllJob() : MessageResponse{
        return scheduleService.deleteAllJob()
    }
    @DeleteMapping("delete/{groupName}/{jobName}")
    fun deleteJob(@PathVariable("groupName") groupName :String,
               @PathVariable("jobName") jobName :String) : MessageResponse{
        return scheduleService.deleteJob(jobName = jobName, groupName=groupName)
    }
    @PutMapping("{groupName}/{jobName}")
    fun updateJob(@RequestBody updatedJob: UpdateJob ,
                  @PathVariable("groupName") groupName :String,
                  @PathVariable("jobName") jobName :String
                  ) : MessageResponse{
        return scheduleService.updateJob(groupName = groupName , jobName = jobName , updatedJob = updatedJob)
    }
}
