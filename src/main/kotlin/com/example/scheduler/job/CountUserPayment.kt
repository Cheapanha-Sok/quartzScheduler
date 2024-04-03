package com.example.scheduler.job

import com.example.scheduler.service.CountUserPaymentService
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.stereotype.Component
import java.util.*

@Component
class CountUserPayment(private val countUserPaymentService: CountUserPaymentService) : Job {
    override fun execute(context: JobExecutionContext?) {
        println("Exceuting 2nd Job" + Date(System.currentTimeMillis()))
    }
}
