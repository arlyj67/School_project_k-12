package com.schoolproject.school_project.entities

import com.schoolproject.school_project.model.JuniorPaymentTerm
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "junior_payments")
data class JuniorPayment (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    var student: Student,

    var amount: Double,

    @Enumerated(EnumType.STRING)
    var term: JuniorPaymentTerm,

    var discountApplied: Double = 0.0,
    var remainingBalance: Double = 0.0,
    var paymentDate: LocalDate,
    var receiptNumber: String,
    var remarks: String? = null,
    var schoolYear: String,
    var paidBy: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processed_By")
    var processedBy: User,

    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),
)