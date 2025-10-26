package com.schoolproject.school_project.repositories

import com.schoolproject.school_project.entities.FeeConfig
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FeeConfigRepository : JpaRepository<FeeConfig, Long> {
    fun findBySchoolYear(schoolYear: String): FeeConfig?
}