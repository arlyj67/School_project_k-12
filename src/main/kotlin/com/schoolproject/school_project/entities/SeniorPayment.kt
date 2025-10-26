package com.schoolproject.school_project.entities

import com.schoolproject.school_project.model.SeniorPaymentTerm
import com.schoolproject.school_project.model.SeniorPaymentType
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "senior_payments")
class SeniorPayment (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    var student: Student,

    var amount: Double,

    @Enumerated(EnumType.STRING)
    var paymentType: SeniorPaymentType,

    @Enumerated(EnumType.STRING)
    var term: SeniorPaymentTerm,

    var discountApplied: Double = 0.0,
    var remainingBalance: Double = 0.0,
    var paymentDate: LocalDate,
    var receiptNumber: String,
    var remarks: String,
    var schoolYear: String,
    var paidBy: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processed_by")
    var processedBy: User,

    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)