package com.example.scheduler.dto

import com.example.scheduler.model.InvoiceItem

data class InvoiceItemDto(
    var unitPrice : Double =0.0,
    var productName : String,
)

fun InvoiceItem.toDto (): InvoiceItemDto{
    return InvoiceItemDto(
        unitPrice = this.price,
        productName = this.product!!.name!!,
    )
}