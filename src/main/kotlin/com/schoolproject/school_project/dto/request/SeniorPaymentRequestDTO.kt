package com.schoolproject.school_project.dto.request

import com.schoolproject.school_project.model.SeniorPaymentTerm
import com.schoolproject.school_project.model.SeniorPaymentType

data class SeniorPaymentRequestDTO (
    val studentNumber: String,
    val amount: Double,
    val paymentType: SeniorPaymentType,
    val terms: List<SeniorPaymentTerm>,   // ⬅ multiple terms
    val receiptNumber: String,
    val remarks: String,
    val schoolYear: String,
    val paidBy: String,
)