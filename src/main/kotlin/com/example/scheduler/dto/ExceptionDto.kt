package com.example.scheduler.dto

import java.time.LocalTime

data class ExceptionDto(
    val status :Int ? =null ,
    val message :String?=null ,
    val localTime: LocalTime? =null
) {
}