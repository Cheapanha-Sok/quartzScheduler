package com.example.scheduler.job

import com.example.scheduler.service.SentMailService
import com.example.scheduler.utillities.anotation.Sl4JLogger
import com.example.scheduler.utillities.anotation.Sl4JLogger.Companion.log
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.stereotype.Component
import java.sql.Time
import java.time.Instant


@Component
@Sl4JLogger
class SentMail(
    private val mailService: SentMailService
) : Job {
    override fun execute(context: JobExecutionContext?) {
        log.info("Start execute at : ${Time.from(Instant.now())}")
    }
}