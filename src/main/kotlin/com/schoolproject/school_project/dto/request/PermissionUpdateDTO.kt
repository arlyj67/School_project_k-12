package com.schoolproject.school_project.dto.request

import com.schoolproject.school_project.model.Roles

data class PermissionUpdateDTO (
    val action: String,
    val allowedRoles: Set<Roles>
)