package com.example.scheduler.dto

import com.example.scheduler.model.CountUserPayment

data class CountPaymentDto(
    var username : String,
    var totalPayment : Int = 0,
    var totalPrice : Double=0.00
)

fun CountUserPayment.toDto() = CountPaymentDto(
    username = this.user!!.name!!,
    totalPayment = this.totalPayment,
    totalPrice = this.totalPrice
)