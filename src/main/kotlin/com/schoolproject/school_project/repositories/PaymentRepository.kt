package com.schoolproject.school_project.repositories

import com.schoolproject.school_project.entities.JuniorPayment
import com.schoolproject.school_project.entities.SeniorPayment
import com.schoolproject.school_project.model.JuniorPaymentTerm
import com.schoolproject.school_project.model.SeniorPaymentTerm
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JuniorPaymentRepository : JpaRepository<JuniorPayment, Long> {
    fun findByStudentIdAndTerm(studentId: Long, term: JuniorPaymentTerm): List<JuniorPayment>
    fun findByStudent_StudentNumber(studentNumber: String): List<JuniorPayment>
}

@Repository
interface SeniorPaymentRepository : JpaRepository<SeniorPayment, Long>{
    fun findByStudentIdAndTerm(studentId: Long, term: SeniorPaymentTerm): List<SeniorPayment>
    fun findByStudent_StudentNumber(studentNumber: String): List<SeniorPayment>
}

