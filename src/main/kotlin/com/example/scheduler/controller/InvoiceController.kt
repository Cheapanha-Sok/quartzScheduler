package com.example.scheduler.controller

import com.example.scheduler.base.response.ObjectResponse
import com.example.scheduler.dto.InvoiceDto
import com.example.scheduler.service.InvoiceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/v1/invoice")
class InvoiceController(
    private val invoiceService: InvoiceService
) {

    @GetMapping
    fun index(): ObjectResponse<List<InvoiceDto>>  {
        return invoiceService.index()
    }
}