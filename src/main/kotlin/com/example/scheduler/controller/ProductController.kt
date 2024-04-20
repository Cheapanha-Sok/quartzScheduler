package com.example.scheduler.controller

import com.example.scheduler.base.response.MessageResponse
import com.example.scheduler.dto.SellDto
import com.example.scheduler.model.Product
import com.example.scheduler.model.User
import com.example.scheduler.service.CountUserPaymentService
import com.example.scheduler.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/v1/product")
class ProductController(
    private val productService: ProductService,
    private val countUserPaymentService: CountUserPaymentService) {

    @GetMapping
    fun index() : List<Product>{
        return productService.index()
    }
    @PostMapping
    fun create(@RequestBody newProduct : Product) : MessageResponse{
        return productService.create(newProduct)
    }
    @PostMapping("/sell/{user_id}")
    fun sell(@RequestBody sellDto: SellDto , @PathVariable("user_id") userId : Long) : MessageResponse{
        return productService.sell(sellDto.productId!! , userId = userId)
    }
}