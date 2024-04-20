package com.example.scheduler.job

import com.example.scheduler.repository.UserRepository
import com.example.scheduler.service.CountUserPaymentService
import com.example.scheduler.utillities.anotation.Sl4JLogger
import com.example.scheduler.utillities.anotation.Sl4JLogger.Companion.log
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.quartz.SchedulerException
import org.springframework.stereotype.Component
import java.sql.Time
import java.time.Instant

@Component
@Sl4JLogger
class CountUserPayment(
    private val countUserPaymentService: CountUserPaymentService,
) : Job {
    override fun execute(context: JobExecutionContext?) {
        countUserPaymentService.create()
        log.info("Start execute at : ${Time.from(Instant.now())}")
    }
}
