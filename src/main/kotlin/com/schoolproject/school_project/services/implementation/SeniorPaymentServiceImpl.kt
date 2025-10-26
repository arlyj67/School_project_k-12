package com.schoolproject.school_project.services.implementation

import com.schoolproject.school_project.dto.request.JuniorPaymentRequestDTO
import com.schoolproject.school_project.dto.request.SeniorPaymentRequestDTO
import com.schoolproject.school_project.dto.response.SeniorPaymentResponseDTO
import com.schoolproject.school_project.mapper.toEntity
import com.schoolproject.school_project.mapper.toResponse
import com.schoolproject.school_project.mapper.toResponseDTO
import com.schoolproject.school_project.model.JuniorPaymentTerm
import com.schoolproject.school_project.model.SeniorPaymentTerm
import com.schoolproject.school_project.model.SeniorPaymentType
import com.schoolproject.school_project.repositories.FeeConfigRepository
import com.schoolproject.school_project.repositories.SeniorPaymentRepository
import com.schoolproject.school_project.repositories.StudentRepository
import com.schoolproject.school_project.repositories.UserRepository
import com.schoolproject.school_project.services.SeniorPaymentService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SeniorPaymentServiceImpl(
    private val seniorPaymentRepository: SeniorPaymentRepository,
    private val studentRepository: StudentRepository,
    private val userRepository: UserRepository,
    private val feeConfigRepository: FeeConfigRepository
): SeniorPaymentService {

    override fun recordPayment(dto: SeniorPaymentRequestDTO): List<SeniorPaymentResponseDTO> {
//        Get processor user id from jwt
        val processorUserId = SecurityContextHolder.getContext().authentication.principal as Long

        val student = studentRepository.findByStudentNumber(dto.studentNumber)
            ?: throw NoSuchElementException("No student number $processorUserId found")

        val processorByUser = userRepository.findById(processorUserId)
            .orElseThrow { NoSuchElementException("No user found with id $processorUserId") }

        val feeConfig = feeConfigRepository.findBySchoolYear(dto.schoolYear)
            ?: throw NoSuchElementException("Fee configuration for ${dto.schoolYear} not found")

//        get current school year feeConfig
        val discountApplied = when (dto.paymentType){
            SeniorPaymentType.FULL -> feeConfig.fullyPaymentDiscount
            SeniorPaymentType.SEMESTRAL -> feeConfig.semestralDiscount
            SeniorPaymentType.SCHOLAR -> feeConfig.scholarshipDiscount
        }

//        Map DTO -> Entities, apply discount
        val payments = dto.toEntity(student, processorByUser, discountApplied)
        val saved = seniorPaymentRepository.saveAll(payments)

        return saved.map { it.toResponseDTO() }
    }

    override fun findByStudentAndTerm(studentId: Long, term: SeniorPaymentTerm ): List<SeniorPaymentResponseDTO> {
        return seniorPaymentRepository.findByStudentIdAndTerm(studentId, term)
            .map { it.toResponseDTO() }
    }

    override fun findByStudentNumber(studentNumber: String): List<SeniorPaymentResponseDTO> {
        return seniorPaymentRepository.findByStudent_StudentNumber(studentNumber)
            .map { it.toResponseDTO() }
    }

}