package com.schoolproject.school_project.mapper

import com.schoolproject.school_project.dto.request.JuniorPaymentRequestDTO
import com.schoolproject.school_project.dto.request.SeniorPaymentRequestDTO
import com.schoolproject.school_project.dto.response.JuniorPaymentResponseDTO
import com.schoolproject.school_project.dto.response.SeniorPaymentResponseDTO
import com.schoolproject.school_project.entities.JuniorPayment
import com.schoolproject.school_project.entities.SeniorPayment
import com.schoolproject.school_project.entities.Student
import com.schoolproject.school_project.entities.User
import java.time.LocalDate

fun JuniorPaymentRequestDTO.toEntities(
    student: Student,
    processBy: User
): List<JuniorPayment> {

    val perTermAmount = this.amount / this.terms.size

    return this.terms.map { term ->
        JuniorPayment(
            student = student,
            amount = perTermAmount,
            term = term,
            remainingBalance = 0.0,
            paymentDate = LocalDate.now(),
            receiptNumber = this.receiptNumber,
            remarks = this.remarks,
            schoolYear = this.schoolYear,
            paidBy = this.paidBy,
            processedBy = processBy
        )
    }
}

fun JuniorPayment.toResponse(): JuniorPaymentResponseDTO {
    return JuniorPaymentResponseDTO(
        paymentId = this.id!!,
        studentNumber = this.student.studentNumber,
        amount = this.amount,
        terms = this.term,
        remainingBalance = this.remainingBalance,
        paymentDate = this.paymentDate,
        receiptNumber = this.receiptNumber,
        remarks = this.remarks,
        schoolYear = this.student.schoolYear
    )
}


fun SeniorPaymentRequestDTO.toEntity(
    student: Student,
    processBy: User,
    discountApplied: Double,
): List<SeniorPayment> {

    val perTermAmount = this.amount / this.terms.size

    return this.terms.map { term ->
        SeniorPayment(
            student = student,
            amount = perTermAmount,
            paymentType = this.paymentType,
            term = term,
            discountApplied = discountApplied,
            remainingBalance = 0.0,
            paymentDate = LocalDate.now(),
            receiptNumber = this.receiptNumber,
            remarks = this.remarks,
            schoolYear = this.schoolYear,
            paidBy = this.paidBy,
            processedBy = processBy,
        )
    }
}

fun SeniorPayment.toResponseDTO(): SeniorPaymentResponseDTO {
    return SeniorPaymentResponseDTO(
        paymentId = this.id!!,
        studentNumber = this.student.studentNumber,
        amount = this.amount,
        paymentType = this.paymentType,
        term = this.term,
        discountApplied = this.discountApplied,
        remainingBalance = this.remainingBalance,
        paymentDate = this.paymentDate,
        receiptNumber = this.receiptNumber,
        remarks = this.remarks,
        schoolYear = this.student.schoolYear
    )
}