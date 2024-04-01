package com.example.scheduler.model

import jakarta.persistence.*
import java.util.Date


@Entity
@Table(name = "invoices")
data class Invoice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id :Long = 0,
    @Column(name = "last_create_at")
    var lastCreateAt : Date?=null,
    @Column(name = "total_price")
    var totalPrice : Double = 0.00,
    @OneToMany(mappedBy = "invoice" , fetch = FetchType.LAZY)
    var invoiceItems : List<InvoiceItem> ?=null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    var user : User?=null,
)