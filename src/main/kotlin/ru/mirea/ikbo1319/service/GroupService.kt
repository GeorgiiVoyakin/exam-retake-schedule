package ru.mirea.ikbo1319.service

import org.springframework.stereotype.Service
import ru.mirea.ikbo1319.data.Group
import ru.mirea.ikbo1319.repository.GroupRepository
import javax.transaction.Transactional

@Service
class GroupService(private val groupRepository: GroupRepository) {
    fun save(group: Group) {
        groupRepository.save(group)
    }

    fun findAll(): MutableList<Group> {
        return groupRepository.findAll()
    }

    fun deleteById(id: Long) {
        groupRepository.deleteById(id)
    }

    @Transactional
    fun deleteByName(name: String) {
        groupRepository.deleteByName(name)
    }

    fun findByName(name: String): Group? {
        return groupRepository.findByName(name)
    }

    fun findById(id: Long): Group? {
        return groupRepository.findById(id).orElse(null)
    }
}