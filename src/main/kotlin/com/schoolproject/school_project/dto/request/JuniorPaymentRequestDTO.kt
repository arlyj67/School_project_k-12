package com.schoolproject.school_project.dto.request

import com.schoolproject.school_project.model.JuniorPaymentTerm

data class JuniorPaymentRequestDTO(
    val studentNumber: String,
    val amount: Double,
    val terms: List<JuniorPaymentTerm>,
    val receiptNumber: String,
    val remarks: String? = null,
    val schoolYear: String,
    val paidBy: String,
)