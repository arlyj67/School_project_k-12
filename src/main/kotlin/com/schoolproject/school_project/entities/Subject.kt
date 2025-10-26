package com.schoolproject.school_project.entities

import com.schoolproject.school_project.model.GradeLevel
import jakarta.persistence.*

@Entity
@Table(name = "subjects")
data class Subject (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val gradeLevel: GradeLevel,

)