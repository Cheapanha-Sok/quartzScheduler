package com.example.scheduler.service.impl

import com.example.scheduler.info.TimerInfo
import com.example.scheduler.job.CountUserPayment
import com.example.scheduler.scheduler.MainScheduler
import com.example.scheduler.service.ScheduleForeverService
import org.springframework.stereotype.Service


@Service
class ScheduleForeverServiceImpl(
    private val mainScheduler: MainScheduler,
) : ScheduleForeverService {
    override fun run() {
        val info : TimerInfo=TimerInfo(5,false,1500L,1000L,"info")
        mainScheduler.scheduleJobWithPriority(CountUserPayment::class.java , info)
    }
}