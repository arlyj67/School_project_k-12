package com.schoolproject.school_project.dto.request

class FeeConfigRequestDTO (
    val packageName: String,
    val schoolYear: String,
    val tuitionFee: Double,
    val applicationFee: Double,
    val downPayment: Double,
    val earlyPayDiscount: Double = 0.0,
    val siblingPaymentDiscount: Double = 0.0,
    val quarterlyDiscount: Double = 0.0,
    val semestralDiscount: Double = 0.0,
    val scholarshipDiscount: Double = 0.0,
    val fullyPaymentDiscount: Double = 0.0,
    val customDiscount: Double = 0.0,
    val miscellaneousFee: Double = 0.0,
)