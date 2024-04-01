package com.example.scheduler.service

import com.example.scheduler.model.Invoice
import com.example.scheduler.model.Product

interface InvoiceItemService {
    fun create(invoice: Invoice , product: Product)
}