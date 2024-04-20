package com.example.scheduler.controller

import com.example.scheduler.base.response.ObjectResponse
import com.example.scheduler.dto.CountPaymentDto
import com.example.scheduler.service.CountUserPaymentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/v1/countPayment")
class CountUserPaymentController(
    private val countUserPaymentService: CountUserPaymentService
) {
    @GetMapping
    fun index() : ObjectResponse<List<CountPaymentDto>>{
        return countUserPaymentService.index()
    }
}