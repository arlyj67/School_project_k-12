package com.schoolproject.school_project.services

import com.schoolproject.school_project.dto.request.FeeConfigRequestDTO
import com.schoolproject.school_project.dto.response.FeeConfigResponseDTO

interface FeeConfigService {
    fun createOrUpdate(dto: FeeConfigRequestDTO): FeeConfigResponseDTO
    fun findActiveBySchoolYear(schoolYear: String): FeeConfigResponseDTO
    fun listAll(): List<FeeConfigResponseDTO>
}