package com.example.scheduler.model

import jakarta.persistence.*


@Entity
@Table(name = "count_user_payment")
data class CountUserPayment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0,
    @Column(name = "total_item")
    var totalPayment : Int = 0,
    @Column(name = "total_price")
    var totalPrice : Double =0.00,
    @OneToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    var user : User?=null,
)