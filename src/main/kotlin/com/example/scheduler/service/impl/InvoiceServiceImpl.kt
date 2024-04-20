package com.example.scheduler.service.impl

import com.example.scheduler.base.response.ObjectResponse
import com.example.scheduler.dto.InvoiceDto
import com.example.scheduler.dto.toDto
import com.example.scheduler.model.Invoice
import com.example.scheduler.model.Product
import com.example.scheduler.model.User
import com.example.scheduler.repository.InvoiceRepository
import com.example.scheduler.service.CountUserPaymentService
import com.example.scheduler.service.InvoiceItemService
import com.example.scheduler.service.InvoiceService
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*


@Service
class InvoiceServiceImpl(
    private val invoiceRepository: InvoiceRepository,
    private val invoiceItemService: InvoiceItemService,
    private val countUserPaymentService: CountUserPaymentService) : InvoiceService{
    override fun create(user: User, product: Product) {
        val invoice : Optional<Invoice> = invoiceRepository.findByUser(user)
        if (invoice.isPresent){
            invoice.get().lastCreateAt = Date.from(Instant.now())
            invoice.get().user = user
            invoice.get().totalPrice += product.price
            val savedInvoice = invoiceRepository.save(invoice.get())
            invoiceItemService.create(savedInvoice , product)
//            countUserPaymentService.create(savedInvoice , user)
        }else{
            val newInvoice = Invoice()
            newInvoice.lastCreateAt = Date.from(Instant.now())
            newInvoice.user = user
            newInvoice.totalPrice = product.price
            val savedInvoice = invoiceRepository.save(newInvoice)
            invoiceItemService.create(savedInvoice , product)
//            countUserPaymentService.create(savedInvoice , user)
        }
    }

    override fun index(): ObjectResponse<List<InvoiceDto>> {
       val invoice = invoiceRepository.findAll()!!.map { it.toDto() }
        return ObjectResponse(invoice)
    }
}