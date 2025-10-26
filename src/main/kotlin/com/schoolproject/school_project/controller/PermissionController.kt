package com.schoolproject.school_project.controller

import com.schoolproject.school_project.dto.request.PermissionUpdateDTO
import com.schoolproject.school_project.dto.response.PermissionResponseDTO
import com.schoolproject.school_project.exception_handler.responses.ApiResponse
import com.schoolproject.school_project.services.PermissionService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/permissions")
class PermissionController(
    private val permissionService: PermissionService,
) {

    @GetMapping
    @PreAuthorize("hasAnyAuthority('IT_ADMIN', 'PRINCIPAL')")
    fun getAll(): ResponseEntity<ApiResponse<List<PermissionResponseDTO>>>{
        val data = permissionService.getAllPermissions()
        return ResponseEntity.ok(ApiResponse(200, "Permission fetched successfully", data = data))

    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('IT_ADMIN', 'PRINCIPAL')")
    fun update(@RequestBody dto: PermissionUpdateDTO): ResponseEntity<ApiResponse<PermissionResponseDTO>> {
        val updated = permissionService.updatePermission(dto)
        return ResponseEntity.ok(ApiResponse(200, "Permission updated successfully", data = updated))
    }

    @DeleteMapping("/{action}")
    @PreAuthorize("hasAnyAuthority('IT_ADMIN', 'PRINCIPAL')")
    fun delete(@PathVariable action: String): ResponseEntity<ApiResponse<Nothing>> {
        permissionService.deletePermission(action)
        return ResponseEntity.ok(ApiResponse(200, "Permission $action deletes successfully", null))
    }
}