package com.example.scheduler.repository

import com.example.scheduler.base.repository.BaseRepository
import com.example.scheduler.model.User

interface UserRepository : BaseRepository<User , Long> {
}