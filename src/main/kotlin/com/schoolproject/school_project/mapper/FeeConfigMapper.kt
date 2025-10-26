package com.schoolproject.school_project.mapper

import com.schoolproject.school_project.dto.request.FeeConfigRequestDTO
import com.schoolproject.school_project.dto.response.FeeConfigResponseDTO
import com.schoolproject.school_project.entities.FeeConfig

fun FeeConfigRequestDTO.toEntity(updatedByName: String, updatedByEmail: String): FeeConfig {
    return FeeConfig(
        packageName = this.packageName,
        schoolYear = this.schoolYear,
        applicationFee = this.applicationFee,
        downPayment = this.downPayment,
        tuitionFee = this.tuitionFee,
        earlyPayDiscount = this.earlyPayDiscount,
        siblingPaymentDiscount = this.siblingPaymentDiscount,
        quarterlyDiscount = this.quarterlyDiscount,
        semestralDiscount = this.semestralDiscount,
        scholarshipDiscount = this.scholarshipDiscount,
        fullyPaymentDiscount = this.fullyPaymentDiscount,
        miscellaneousFee = this.miscellaneousFee,
        customDiscount = this.customDiscount,
        updatedByName = updatedByName,
        updatedByEmail = updatedByEmail
    )
}

fun FeeConfig.toResponseDTO(): FeeConfigResponseDTO =
    FeeConfigResponseDTO(
        id = id!!,
        schoolYear = schoolYear,
        tuitionFee = tuitionFee,
        applicationFee = applicationFee,
        downPayment = downPayment,
        earlyPayDiscount = earlyPayDiscount,
        siblingPaymentDiscount = siblingPaymentDiscount,
        quarterlyDiscount = quarterlyDiscount,
        semestralDiscount = semestralDiscount,
        scholarshipDiscount = scholarshipDiscount,
        fullyPaymentDiscount = fullyPaymentDiscount,
        miscellaneousFee = miscellaneousFee,
        customDiscount = customDiscount,
        isActive = isActive,
        updatedByName = this.updatedByName,
        updatedByEmail = this.updatedByEmail,
        createdAt = createdAt,
        updatedAt = updatedAt
    )