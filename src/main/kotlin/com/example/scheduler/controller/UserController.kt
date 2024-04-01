package com.example.scheduler.controller

import com.example.scheduler.model.User
import com.example.scheduler.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/v1/user")
class UserController(private val userService: UserService) {

    @GetMapping
    fun index(): List<User>{
        return userService.index()
    }
    @PostMapping
    fun create(@RequestBody newUser : User){
        return userService.create(newUser)
    }
}