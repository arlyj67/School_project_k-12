package com.schoolproject.school_project.services.implementation

import com.schoolproject.school_project.dto.request.JuniorPaymentRequestDTO
import com.schoolproject.school_project.dto.response.JuniorPaymentResponseDTO
import com.schoolproject.school_project.mapper.toEntities
import com.schoolproject.school_project.mapper.toResponse
import com.schoolproject.school_project.model.JuniorPaymentTerm
import com.schoolproject.school_project.repositories.JuniorPaymentRepository
import com.schoolproject.school_project.repositories.StudentRepository
import com.schoolproject.school_project.repositories.UserRepository
import com.schoolproject.school_project.services.JuniorPaymentService
import jakarta.transaction.Transactional
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


@Service
class JuniorPaymentServiceImpl(
    private val juniorPaymentRepository: JuniorPaymentRepository,
    private val studentRepository: StudentRepository,
    private val userRepository: UserRepository
): JuniorPaymentService {

    @Transactional
    override fun recordPayment(dto: JuniorPaymentRequestDTO): List<JuniorPaymentResponseDTO> {

        val processorUserId = SecurityContextHolder.getContext().authentication.principal as Long

        val student = studentRepository.findByStudentNumber(dto.studentNumber)
            ?: throw NoSuchElementException("No student ${dto.studentNumber} found")

        val processedByUser = userRepository.findById(processorUserId)
            .orElseThrow { NoSuchElementException("User $processorUserId not found") }

        val payment = dto.toEntities(student, processedByUser)
        val savedPayments = juniorPaymentRepository.saveAll(payment)

        return savedPayments.map { it.toResponse() }
    }
    override fun findByStudentAndTerm(studentId: Long, term: JuniorPaymentTerm): List<JuniorPaymentResponseDTO> {
        return juniorPaymentRepository.findByStudentIdAndTerm(studentId, term)
            .map { it.toResponse() }
    }

    override fun findByStudentNumber(studentNumber: String): List<JuniorPaymentResponseDTO> {
        return juniorPaymentRepository.findByStudent_StudentNumber(studentNumber)
        .map { it.toResponse() }
    }
}