package com.example.scheduler.job

import com.example.scheduler.repository.UserRepository
import com.example.scheduler.service.CountUserPaymentService
import com.example.scheduler.utillities.anotation.Sl4JLogger
import com.example.scheduler.utillities.anotation.Sl4JLogger.Companion.log
import com.example.scheduler.utillities.quartz.Timer
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.stereotype.Component
import java.sql.Time
import java.time.Instant
import java.util.*

@Component
@Sl4JLogger
class CountUserPayment(private val countUserPaymentService: CountUserPaymentService,
    private val userRepository: UserRepository) : Job {
    override fun execute(context: JobExecutionContext?) {
       log.info("Cron expression ${Time.from(Instant.now())}" )
    }
}
