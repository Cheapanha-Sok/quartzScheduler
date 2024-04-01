package com.example.scheduler.service

import com.example.scheduler.model.Product
import com.example.scheduler.model.User

interface UserService {
    fun index() : List<User>
    fun create (newUser : User)
}