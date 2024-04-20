package com.example.scheduler.service.impl

import com.example.scheduler.model.User
import com.example.scheduler.repository.UserRepository
import com.example.scheduler.service.SentMailService
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service


@Service
class SentMailServiceImpl(
    private val mailSender: JavaMailSender,
    private val userRepository: UserRepository
) : SentMailService {

    @Value("\${spring.mail.username}")
    private val username: String? = null

    override fun sentMail() {
        val users : List<User> = userRepository.findAll()!!
        val simpleMailMessage : SimpleMailMessage = SimpleMailMessage()
        users.map {
            simpleMailMessage.from = username
            simpleMailMessage.subject = "nham by nv"
            simpleMailMessage.text = "nham bay hx hx"
            simpleMailMessage.setTo(it.email)
            mailSender.send(simpleMailMessage)
        }
    }
}