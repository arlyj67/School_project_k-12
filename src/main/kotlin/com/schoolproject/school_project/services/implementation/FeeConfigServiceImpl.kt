package com.schoolproject.school_project.services.implementation

import com.schoolproject.school_project.dto.request.FeeConfigRequestDTO
import com.schoolproject.school_project.dto.response.FeeConfigResponseDTO
import com.schoolproject.school_project.entities.FeeConfig
import com.schoolproject.school_project.exception_handler.NotFoundException
import com.schoolproject.school_project.mapper.toResponseDTO
import com.schoolproject.school_project.repositories.AdministrationRepository
import com.schoolproject.school_project.repositories.FeeConfigRepository
import com.schoolproject.school_project.services.FeeConfigService
import jakarta.transaction.Transactional
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class FeeConfigServiceImpl(
    private val feeConfigRepository: FeeConfigRepository,
    private val administrationRepository: AdministrationRepository
) : FeeConfigService {
    
    @Transactional
    override fun createOrUpdate(dto: FeeConfigRequestDTO): FeeConfigResponseDTO {

        val processByUserId = SecurityContextHolder.getContext().authentication.principal as? Long
            ?: throw IllegalArgumentException("Invalid user in security context")

        val admin = administrationRepository.findByUserId(processByUserId)
            ?: throw NotFoundException("Admin not found for user $processByUserId")

        var updatedByAdminName = "${admin.firstName} ${admin.lastName}"
        var updatedByAdminEmail = admin.user.username

        val existing = feeConfigRepository.findBySchoolYear(dto.schoolYear)

        val entity = existing?.apply {
            this.packageName = dto.packageName
            this.tuitionFee = dto.tuitionFee
            this.applicationFee = dto.applicationFee
            this.downPayment = dto.downPayment
            this.earlyPayDiscount = dto.earlyPayDiscount
            this.siblingPaymentDiscount = dto.siblingPaymentDiscount
            this.quarterlyDiscount = dto.quarterlyDiscount
            this.semestralDiscount = dto.semestralDiscount
            this.scholarshipDiscount = dto.scholarshipDiscount
            this.fullyPaymentDiscount = dto.fullyPaymentDiscount
            this.customDiscount = dto.customDiscount
            this.miscellaneousFee = dto.miscellaneousFee
            updatedByAdminName = updatedByName
            updatedByAdminEmail = updatedByEmail
            this.updatedAt = LocalDateTime.now()
        }
            ?: FeeConfig(
                packageName = dto.packageName,
                schoolYear = dto.schoolYear,
                tuitionFee = dto.tuitionFee,
                applicationFee = dto.applicationFee,
                downPayment = dto.downPayment,
                quarterlyDiscount = dto.quarterlyDiscount,
                semestralDiscount = dto.semestralDiscount,
                fullyPaymentDiscount = dto.fullyPaymentDiscount,
                updatedByName = updatedByAdminName,
                updatedByEmail = updatedByAdminEmail
            )

        val saved = feeConfigRepository.save(entity)

        return saved.toResponseDTO()
    }

    override fun findActiveBySchoolYear(schoolYear: String): FeeConfigResponseDTO {
        val config = feeConfigRepository.findBySchoolYear(schoolYear)
            ?: throw NotFoundException("Fee config not found for school year $schoolYear")

        return config.toResponseDTO()
    }

    override fun listAll(): List<FeeConfigResponseDTO> {
        return feeConfigRepository.findAll()
            .map { it.toResponseDTO() }
    }
}