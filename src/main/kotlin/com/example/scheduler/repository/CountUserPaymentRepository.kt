package com.example.scheduler.repository

import com.example.scheduler.base.repository.BaseRepository
import com.example.scheduler.model.CountUserPayment
import com.example.scheduler.model.User
import java.util.Optional

interface CountUserPaymentRepository : BaseRepository<CountUserPayment , Long> {
    fun findByUser(user: User): Optional<CountUserPayment>
}