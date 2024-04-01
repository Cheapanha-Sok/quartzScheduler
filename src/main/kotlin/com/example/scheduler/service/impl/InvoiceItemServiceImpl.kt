package com.example.scheduler.service.impl

import com.example.scheduler.model.Invoice
import com.example.scheduler.model.InvoiceItem
import com.example.scheduler.model.Product
import com.example.scheduler.repository.InvoiceItemRepository
import com.example.scheduler.service.InvoiceItemService
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*


@Service
class InvoiceItemServiceImpl(
    private val invoiceItemRepository: InvoiceItemRepository
) : InvoiceItemService {

    override fun create(invoice: Invoice, product: Product) {
        val invoiceItem = InvoiceItem()
        invoiceItem.invoice = invoice
        invoiceItem.product = product
        invoiceItem.createAt = Date.from(Instant.now())
        invoiceItem.price = product.price
        invoiceItemRepository.save(invoiceItem)
    }

}