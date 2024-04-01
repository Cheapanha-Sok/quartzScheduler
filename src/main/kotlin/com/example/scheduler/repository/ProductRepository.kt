package com.example.scheduler.repository

import com.example.scheduler.base.repository.BaseRepository
import com.example.scheduler.model.Product

interface ProductRepository : BaseRepository<Product , Long> {
}