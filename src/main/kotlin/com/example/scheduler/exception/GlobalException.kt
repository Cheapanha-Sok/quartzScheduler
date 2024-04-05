package com.example.scheduler.exception

import com.example.scheduler.dto.ExceptionDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalTime


@RestControllerAdvice
class GlobalException {
    @ExceptionHandler(InvalidDateException::class)
    fun invalidDateHandler(ex: InvalidDateException): ResponseEntity<ExceptionDto> {
        val errorMessage = ExceptionDto(
            HttpStatus.NOT_FOUND.value(),
            ex.message,
            localTime = LocalTime.now()
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NotFoundException::class)
    fun notFoundHandler(ex: NotFoundException): ResponseEntity<ExceptionDto> {
        val errorMessage = ExceptionDto(
            HttpStatus.NOT_FOUND.value(),
            ex.message,
            localTime = LocalTime.now()
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }
}