package com.schoolproject.school_project.repositories

import com.schoolproject.school_project.entities.Permission
import org.springframework.data.jpa.repository.JpaRepository

interface PermissionRepository: JpaRepository<Permission, Long> {
    fun findByAction(action: String): Permission?
}