package com.schoolproject.school_project.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "fee_config")
class FeeConfig (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var packageName: String?,
    var schoolYear: String,
    var tuitionFee: Double,
    var applicationFee: Double,
    var downPayment: Double,

    var earlyPayDiscount: Double = 0.0,
    var siblingPaymentDiscount: Double = 0.0,
    var quarterlyDiscount: Double = 0.0,  // e.g., 0.02 = 2%
    var semestralDiscount: Double = 0.0,  // e.g., 0.05 = 5%
    var scholarshipDiscount: Double = 0.0,  // e.g., 0.05 = 5%
    var fullyPaymentDiscount: Double = 0.0, // e.g., 0.10 = 10%
    var miscellaneousFee: Double = 0.0,
    var customDiscount: Double = 0.0,

    var isActive: Boolean = true,

    var updatedByName: String,
    var updatedByEmail: String? = null,

    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)