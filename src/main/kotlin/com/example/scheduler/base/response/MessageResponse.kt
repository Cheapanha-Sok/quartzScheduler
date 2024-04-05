package com.example.scheduler.base.response

import org.springframework.stereotype.Component

@Component
class MessageResponse {
    var response : ResponseMessage ? = ResponseMessage().response()
}