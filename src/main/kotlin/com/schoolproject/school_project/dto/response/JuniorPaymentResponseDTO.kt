package com.schoolproject.school_project.dto.response

import com.schoolproject.school_project.model.JuniorPaymentTerm
import java.time.LocalDate

data class JuniorPaymentResponseDTO(
    val paymentId: Long,
    val studentNumber: String,
    val amount: Double,
    val terms: JuniorPaymentTerm,
    val remainingBalance: Double,
    val paymentDate: LocalDate,
    val receiptNumber: String,
    val remarks: String?,
    val schoolYear: String
)