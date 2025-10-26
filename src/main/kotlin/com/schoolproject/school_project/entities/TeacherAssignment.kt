package com.schoolproject.school_project.entities

import jakarta.persistence.*
import java.time.DayOfWeek
import java.time.LocalTime

@Entity
@Table(
    name = "teacher_assignments",
    uniqueConstraints = [UniqueConstraint(columnNames = ["teacher_id", "room_id", "subject_id", "day_of_week", "start_time"])]
)
data class TeacherAssignment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    var teacher: Administration,

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    var room: Room,

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    var subject: Subject,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var dayOfWeek: DayOfWeek,

    @Column(nullable = false)
    var startTime: LocalTime,

    @Column(nullable = false)
    var endTime: LocalTime,

    )