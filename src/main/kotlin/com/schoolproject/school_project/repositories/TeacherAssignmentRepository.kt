package com.schoolproject.school_project.repositories

import com.schoolproject.school_project.entities.TeacherAssignment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalTime

@Repository
interface TeacherAssignmentRepository : JpaRepository<TeacherAssignment, Long> {

    fun findAllByTeacherId(teacherId : Long) : List<TeacherAssignment>

    @Query("""
        SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END
        FROM TeacherAssignment t
        WHERE t.teacher.id = :teacherId
        AND t.dayOfWeek = :dayOfWeek
        AND t.startTime < :endTime
        AND t.endTime > :startTime
    """)
    fun hasTeacherConflict(
        teacherId: Long,
        dayOfWeek: java.time.DayOfWeek,
        startTime: java.time.LocalTime,
        endTime: java.time.LocalTime,
    ): Boolean

    @Query("""
        SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END
        FROM TeacherAssignment t
        WHERE t.room.id = :roomId
        AND t.dayOfWeek = :dayOfWeek
        AND t.startTime < :endTime
        AND t.endTime > :startTime
    """)
    fun hasRoomConflict(
        roomId: Long,
        dayOfWeek: java.time.DayOfWeek,
        startTime: java.time.LocalTime,
        endTime: java.time.LocalTime,
    ): Boolean
}