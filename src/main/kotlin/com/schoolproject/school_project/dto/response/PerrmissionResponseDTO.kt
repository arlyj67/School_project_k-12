package com.schoolproject.school_project.dto.response

import com.schoolproject.school_project.model.Roles

data class PermissionResponseDTO (
    val action: String,
    val allowedRoles: Set<Roles>,
)