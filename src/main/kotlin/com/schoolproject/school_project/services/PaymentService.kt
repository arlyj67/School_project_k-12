package com.schoolproject.school_project.services

import com.schoolproject.school_project.dto.request.JuniorPaymentRequestDTO
import com.schoolproject.school_project.dto.request.SeniorPaymentRequestDTO
import com.schoolproject.school_project.dto.response.JuniorPaymentResponseDTO
import com.schoolproject.school_project.dto.response.SeniorPaymentResponseDTO
import com.schoolproject.school_project.entities.JuniorPayment
import com.schoolproject.school_project.model.JuniorPaymentTerm
import com.schoolproject.school_project.model.SeniorPaymentTerm

interface JuniorPaymentService {
    fun recordPayment(dto: JuniorPaymentRequestDTO): List<JuniorPaymentResponseDTO>
    fun findByStudentAndTerm(studentId: Long, term: JuniorPaymentTerm): List<JuniorPaymentResponseDTO>
    fun findByStudentNumber(studentNumber: String): List<JuniorPaymentResponseDTO>
}

interface SeniorPaymentService {
    fun recordPayment(dto: SeniorPaymentRequestDTO): List<SeniorPaymentResponseDTO>
    fun findByStudentAndTerm(studentId: Long, term: SeniorPaymentTerm): List<SeniorPaymentResponseDTO>
    fun findByStudentNumber(studentNumber: String): List<SeniorPaymentResponseDTO>
}