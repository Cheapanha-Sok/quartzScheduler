package com.example.scheduler.service.impl

import com.example.scheduler.info.TimerInfo
import com.example.scheduler.job.CountUserPayment
import com.example.scheduler.scheduler.MainScheduler
import com.example.scheduler.service.ScheduleForeverService
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service


@Service
class ScheduleForeverServiceImpl(
    private val mainScheduler: MainScheduler,
) : ScheduleForeverService {

    @PostConstruct
    override fun run() {
        val info : TimerInfo= TimerInfo()
        info.isRunForever = false
        info.callBackData = "my call Back"
        info.totalFireCount = 5
        info.remainingFireCount = info.totalFireCount
        info.initialOffsetMs = 1000L
        info.repeatIntervalMs = 5000L
        mainScheduler.scheduleJobWithPriority(CountUserPayment::class.java, info)
    }
}