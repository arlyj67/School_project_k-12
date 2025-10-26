package com.schoolproject.school_project.services.implementation

import com.schoolproject.school_project.dto.request.SubjectCreateDTO
import com.schoolproject.school_project.dto.response.SubjectResponseDTO
import com.schoolproject.school_project.entities.Subject
import com.schoolproject.school_project.exception_handler.NotFoundException
import com.schoolproject.school_project.exception_handler.responses.ApiResponse
import com.schoolproject.school_project.mapper.toResponseDTO
import com.schoolproject.school_project.model.GradeLevel
import com.schoolproject.school_project.repositories.AdministrationRepository
import com.schoolproject.school_project.repositories.SubjectRepository
import com.schoolproject.school_project.services.SubjectService
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class SubjectServiceImpl(
    private val subjectRepository: SubjectRepository,
    private val administrationRepository: AdministrationRepository,
) : SubjectService {

    override fun create(dto: SubjectCreateDTO): SubjectResponseDTO {
        if (subjectRepository.existsByNameAndGradeLevel(dto.name, dto.gradeLevel)) {
            throw RuntimeException("A subject already exists")
        }

        val subject = Subject(name = dto.name, gradeLevel = dto.gradeLevel)
        return subjectRepository.save(subject).toResponseDTO()
    }

    override fun findAll(): List<SubjectResponseDTO> {
        return subjectRepository.findAll().map { it.toResponseDTO() }
    }

    override fun findByGradeLevel(gradeLevel: GradeLevel): List<SubjectResponseDTO> {
        return subjectRepository.findAllByGradeLevel(gradeLevel).map { it.toResponseDTO() }
    }

}