package com.schoolproject.school_project.dto.response

import java.time.LocalDateTime

data class FeeConfigResponseDTO (
    val id: Long,
    val schoolYear: String,
    val tuitionFee: Double = 0.0,
    val applicationFee: Double = 0.0,
    val downPayment: Double = 0.0,
    val earlyPayDiscount: Double = 0.0,
    val siblingPaymentDiscount: Double = 0.0,
    val quarterlyDiscount: Double = 0.0,
    val semestralDiscount: Double = 0.0,
    val fullyPaymentDiscount: Double = 0.0,
    val scholarshipDiscount: Double = 0.0,
    val miscellaneousFee: Double = 0.0,
    val customDiscount: Double = 0.0,
    val isActive: Boolean,
    val updatedByName: String?,
    val updatedByEmail: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,

)