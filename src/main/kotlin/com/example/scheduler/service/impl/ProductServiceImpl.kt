package com.example.scheduler.service.impl

import com.example.scheduler.base.response.MessageResponse
import com.example.scheduler.dto.SellDto
import com.example.scheduler.model.Product
import com.example.scheduler.repository.ProductRepository
import com.example.scheduler.repository.UserRepository
import com.example.scheduler.service.CountUserPaymentService
import com.example.scheduler.service.InvoiceService
import com.example.scheduler.service.ProductService
import org.springframework.stereotype.Service


@Service
class ProductServiceImpl (
    private val productRepository: ProductRepository,
    private val invoiceService: InvoiceService,
    private val userRepository: UserRepository,
    private val countUserPaymentService: CountUserPaymentService): ProductService {
    override fun create(newProduct: Product) : MessageResponse {
        productRepository.save(newProduct).let { return MessageResponse() }
    }

    override fun index(): List<Product> {
        return productRepository.findAll()!!
    }

    override fun sell(productId: Long , userId : Long):MessageResponse {
        val product = productRepository.findById(productId)!!.orElseThrow { RuntimeException("Product with id $productId not found") }
        val user = userRepository.findById(userId)!!.orElseThrow { RuntimeException("User with id $userId not found") }
        invoiceService.create(user, product).let { return MessageResponse() }

    }
}