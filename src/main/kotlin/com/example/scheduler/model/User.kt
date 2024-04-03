package com.example.scheduler.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
@Table(name = "users")
@JsonIgnoreProperties("invoices , countUserPayment")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id :Long = 0,
    var name :String?=null,
    var email :String?=null,
    var password :String?=null,
    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
    var invoices : List<Invoice> ?=null,
    @OneToOne(mappedBy = "user")
    var countUserPayment : CountUserPayment?=null,
)
