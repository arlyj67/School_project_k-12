package com.schoolproject.school_project.repositories

import com.schoolproject.school_project.entities.Administration
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdministrationRepository : JpaRepository<Administration, Long> {
    fun findByUserId(adminId: Long): Administration?
}