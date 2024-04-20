package com.example.scheduler.service

import com.example.scheduler.base.response.ObjectResponse
import com.example.scheduler.dto.InvoiceDto
import com.example.scheduler.model.Product
import com.example.scheduler.model.User

interface InvoiceService {
    fun create(user : User ,product: Product )
    fun index() : ObjectResponse<List<InvoiceDto>>
}