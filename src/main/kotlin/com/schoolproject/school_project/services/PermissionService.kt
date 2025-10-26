package com.schoolproject.school_project.services

import com.schoolproject.school_project.dto.request.PermissionUpdateDTO
import com.schoolproject.school_project.dto.response.PermissionResponseDTO

interface PermissionService {
    fun getAllPermissions(): List<PermissionResponseDTO>
    fun updatePermission(dto: PermissionUpdateDTO): PermissionResponseDTO
    fun deletePermission(action: String)
}