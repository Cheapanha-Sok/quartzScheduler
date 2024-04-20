package com.example.scheduler.dto

import com.example.scheduler.model.Invoice

data class InvoiceDto(
    var username : String,
    var totalPrice : Double=0.0,
    var invoiceItem : List<InvoiceItemDto>
)

fun Invoice.toDto() : InvoiceDto{
    return InvoiceDto(
        username = this.user!!.name!!,
        totalPrice = this.totalPrice,
        invoiceItem = this.invoiceItems!!.map { it.toDto() }
    )
}