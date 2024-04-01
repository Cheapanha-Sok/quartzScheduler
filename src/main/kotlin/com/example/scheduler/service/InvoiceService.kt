package com.example.scheduler.service

import com.example.scheduler.model.Product
import com.example.scheduler.model.User

interface InvoiceService {
    fun create(user : User ,product: Product )
}