package com.example.scheduler.model

import jakarta.persistence.*
import java.util.Date


@Entity
@Table(name = "invoice_items")
data class InvoiceItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id :Long =0,
    var price : Double = 0.00,
    @Column(name = "create_at")
    var createAt : Date ? =null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id" , referencedColumnName = "id")
    var product : Product?=null,
    @ManyToOne
    @JoinColumn(name = "invoice_id" , referencedColumnName = "id")
    var invoice : Invoice?=null,


)