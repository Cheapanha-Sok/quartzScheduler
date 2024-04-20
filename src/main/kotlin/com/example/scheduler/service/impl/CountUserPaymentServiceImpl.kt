package com.example.scheduler.service.impl

import com.example.scheduler.base.response.ObjectResponse
import com.example.scheduler.dto.CountPaymentDto
import com.example.scheduler.dto.toDto
import com.example.scheduler.model.CountUserPayment
import com.example.scheduler.model.Invoice
import com.example.scheduler.repository.CountUserPaymentRepository
import com.example.scheduler.repository.InvoiceRepository
import com.example.scheduler.service.CountUserPaymentService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service

class CountUserPaymentServiceImpl(
    private val countUserRepository: CountUserPaymentRepository,
    private val invoiceRepository: InvoiceRepository
) : CountUserPaymentService {
    //    override fun create(invoice: Invoice , user : User) {
//        val existingUser = countUserRepository.findByUser(user)
//        if (existingUser.isPresent){
//            existingUser.get().totalPayment = invoice.invoiceItems!!.count()
//            existingUser.get().totalPrice = invoice.totalPrice
//            countUserRepository.save(existingUser.get())
//        }else{
//            val countUserPayment = CountUserPayment()
//            countUserPayment.user = user
//            countUserPayment.totalPrice = invoice.totalPrice
//            countUserPayment.totalPayment = invoice.invoiceItems!!.count()
//            countUserRepository.save(countUserPayment)
//        }
//    }
    @Transactional
    override fun create() {
        val invoices: List<Invoice> = invoiceRepository.findAll()!!
        val newRecord = CountUserPayment()
        invoices.map {
            val countUserPayment = countUserRepository.findByUser(it.user!!)
            if (countUserPayment.isPresent) {
                countUserPayment.get().totalPrice = it.totalPrice
                countUserPayment.get().totalPayment = it.invoiceItems!!.count()
                countUserRepository.save(countUserPayment.get())
            } else {
                newRecord.user = it.user
                newRecord.totalPrice = it.totalPrice
                newRecord.totalPayment = it.invoiceItems!!.count()
                countUserRepository.save(newRecord)
            }
        }
    }

    override fun index(): ObjectResponse<List<CountPaymentDto>> {
        val countPayment = countUserRepository.findAll()!!.map { it.toDto() }
        return ObjectResponse(countPayment)
    }
}