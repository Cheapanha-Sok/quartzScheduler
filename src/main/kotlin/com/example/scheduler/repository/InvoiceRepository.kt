package com.example.scheduler.repository

import com.example.scheduler.base.repository.BaseRepository
import com.example.scheduler.model.Invoice
import com.example.scheduler.model.User
import java.util.Optional

interface InvoiceRepository : BaseRepository<Invoice , Long> {
    fun findByUser(user: User) : Optional<Invoice>
}