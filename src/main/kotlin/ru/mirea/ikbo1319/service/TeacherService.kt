package ru.mirea.ikbo1319.service

import org.springframework.stereotype.Service
import ru.mirea.ikbo1319.data.Teacher
import ru.mirea.ikbo1319.repository.TeacherRepository

@Service
class TeacherService(private val teacherRepository: TeacherRepository) {
    fun findAll(): List<Teacher> {
        return teacherRepository.findAll()
    }

    fun findById(id: Long): Teacher? {
        return teacherRepository.findById(id).orElse(null)
    }

    fun getByName(name: String): Teacher? {
        return teacherRepository.getByName(name)
    }

    fun save(teacher: Teacher) {
        teacherRepository.save(teacher)
    }

    fun deleteById(id: Long) {
        teacherRepository.deleteById(id)
    }
}