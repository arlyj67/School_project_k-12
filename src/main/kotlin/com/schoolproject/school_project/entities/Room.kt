package com.schoolproject.school_project.entities

import com.schoolproject.school_project.model.GradeLevel
import jakarta.persistence.*

@Entity
@Table(name = "rooms")
data class Room (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToMany
    @JoinTable(
        name = "teacher_assignments",
        joinColumns = [JoinColumn(name = "room_id")],
        inverseJoinColumns = [JoinColumn(name = "teacher_id")]
    )
    var teachers: MutableSet<Administration> = mutableSetOf(),

    @Column(nullable = false, unique = true)
    var classroom: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var gradeLevel: GradeLevel,

    @Column(nullable = false)
    var capacity: Int,

)