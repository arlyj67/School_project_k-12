package com.schoolproject.school_project.services

import com.schoolproject.school_project.entities.Permission
import com.schoolproject.school_project.repositories.PermissionRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component("authPermission")
class AuthPermissionService (
    private val permissionRepository: PermissionRepository,
){
    fun hasAccess(action: String): Boolean {
        val auth = SecurityContextHolder.getContext().authentication
        val roles = auth.authorities.map { it.authority }

        val allowedRoles = permissionRepository.findByAction(action)
            ?.allowedRoles?.map { it.name }?: return false

        return roles.any{ it in allowedRoles }
    }
}