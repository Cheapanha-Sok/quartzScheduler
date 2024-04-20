package com.example.scheduler.service

import com.example.scheduler.base.response.ObjectResponse
import com.example.scheduler.dto.CountPaymentDto
import com.example.scheduler.model.Invoice
import com.example.scheduler.model.User



interface CountUserPaymentService {
    fun create()
    fun index() : ObjectResponse<List<CountPaymentDto>>
}