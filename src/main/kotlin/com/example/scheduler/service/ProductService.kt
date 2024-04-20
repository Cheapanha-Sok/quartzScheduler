package com.example.scheduler.service

import com.example.scheduler.base.response.MessageResponse
import com.example.scheduler.dto.SellDto
import com.example.scheduler.model.Product

interface ProductService {
    fun create (newProduct : Product): MessageResponse
    fun index() :List<Product>
    fun sell(productId : Long, userId : Long) : MessageResponse
}