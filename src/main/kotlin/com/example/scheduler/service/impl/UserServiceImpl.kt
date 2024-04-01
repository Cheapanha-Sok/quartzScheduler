package com.example.scheduler.service.impl

import com.example.scheduler.model.User
import com.example.scheduler.repository.UserRepository
import com.example.scheduler.service.CountUserPaymentService
import com.example.scheduler.service.UserService
import org.springframework.stereotype.Service


@Service
class UserServiceImpl  (
    private var userRepository: UserRepository,
    private val countUserPaymentService: CountUserPaymentService) : UserService {
    override fun index(): List<User> {
        countUserPaymentService.create()
        return userRepository.findAll()!!
    }

    override fun create(newUser: User) {
        userRepository.save(newUser)
    }
}