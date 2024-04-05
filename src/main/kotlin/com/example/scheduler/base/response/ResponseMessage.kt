package com.example.scheduler.base.response

import org.springframework.stereotype.Component

@Component
class ResponseMessage (
    var code : Int ?= 200,
    var message: String ?= "Success",
) {
    fun response() = ResponseMessage()
}