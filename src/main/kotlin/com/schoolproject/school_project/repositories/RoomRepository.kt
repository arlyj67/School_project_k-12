package com.schoolproject.school_project.repositories

import com.schoolproject.school_project.entities.Room
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomRepository : JpaRepository<Room, Long>{
    fun existsByClassroom(name: String): Boolean
}