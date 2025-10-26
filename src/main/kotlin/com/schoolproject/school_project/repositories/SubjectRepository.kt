package com.schoolproject.school_project.repositories

import com.schoolproject.school_project.entities.Subject
import com.schoolproject.school_project.model.GradeLevel
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectRepository : JpaRepository<Subject, Long> {
    fun existsByNameAndGradeLevel(name: String, gradeLevel: GradeLevel): Boolean
    fun findAllByGradeLevel(gradeLevel: GradeLevel): List<Subject>
}