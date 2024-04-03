package com.example.scheduler.job

import com.example.scheduler.service.CountUserPaymentService
import com.example.scheduler.utillities.anotation.Sl4JLogger
import com.example.scheduler.utillities.anotation.Sl4JLogger.Companion.log
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.stereotype.Component
import java.util.*

@Component
@Sl4JLogger
class CountUserPayment(private val countUserPaymentService: CountUserPaymentService) : Job {
    override fun execute(context: JobExecutionContext?) {
        log.info("The job is executing" + Date(System.currentTimeMillis()))
    }
}
