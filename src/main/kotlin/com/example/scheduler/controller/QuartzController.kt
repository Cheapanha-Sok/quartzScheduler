package com.example.scheduler.controller

import com.example.scheduler.scheduler.MainScheduler
import com.example.scheduler.service.ScheduleForeverService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/v1/schedule")
class QuartzController(
    private var scheduleForeverService: ScheduleForeverService
) {
    @GetMapping
    fun scheduleJob() {
        scheduleForeverService.run()
    }
}