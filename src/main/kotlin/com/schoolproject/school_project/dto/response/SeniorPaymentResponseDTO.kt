package com.schoolproject.school_project.dto.response

import com.schoolproject.school_project.model.SeniorPaymentTerm
import com.schoolproject.school_project.model.SeniorPaymentType
import java.time.LocalDate

data class SeniorPaymentResponseDTO (
    val paymentId: Long,
    val studentNumber: String,
    val amount: Double,
    val paymentType: SeniorPaymentType,
    val term: SeniorPaymentTerm,
    val discountApplied: Double,
    val remainingBalance: Double,
    val paymentDate: LocalDate,
    val receiptNumber: String,
    val remarks: String?,
    val schoolYear: String
)