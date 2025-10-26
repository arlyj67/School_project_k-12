package com.schoolproject.school_project.services.implementation

import com.schoolproject.school_project.dto.request.PermissionUpdateDTO
import com.schoolproject.school_project.dto.response.PermissionResponseDTO
import com.schoolproject.school_project.entities.Permission
import com.schoolproject.school_project.exception_handler.ResourceNotFoundException
import com.schoolproject.school_project.repositories.PermissionRepository
import com.schoolproject.school_project.services.PermissionService
import org.springframework.stereotype.Service
import kotlin.concurrent.thread

@Service
class PermissionServiceImpl(
    private val permissionRepository: PermissionRepository,
) : PermissionService {
    override fun getAllPermissions(): List<PermissionResponseDTO> {
        return permissionRepository.findAll().map {
            PermissionResponseDTO(
                action = it.action,
                allowedRoles = it.allowedRoles,
            )
        }
    }

    override fun updatePermission(dto: PermissionUpdateDTO): PermissionResponseDTO {
        val permission = permissionRepository.findByAction(dto.action)?.apply {
            allowedRoles = dto.allowedRoles.toMutableSet()
        } ?: Permission(
            action = dto.action,
            allowedRoles = dto.allowedRoles.toMutableSet()
        )

        val saved = permissionRepository.save(permission)

        return PermissionResponseDTO(
            action = saved.action,
            allowedRoles = saved.allowedRoles
        )
    }

    override fun deletePermission(action: String) {
        val permission = permissionRepository.findByAction(action)
            ?: throw ResourceNotFoundException("Permission with action $action not found")

        permissionRepository.delete(permission)
    }


}