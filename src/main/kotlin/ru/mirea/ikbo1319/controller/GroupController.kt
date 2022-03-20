package ru.mirea.ikbo1319.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.mirea.ikbo1319.data.Group
import ru.mirea.ikbo1319.dto.GroupDTO
import ru.mirea.ikbo1319.service.GroupService

@RestController
class GroupController(private val groupService: GroupService) {
    @PostMapping("/api/schedule/group")
    fun addGroup(@RequestParam name: String, @RequestParam course: Int): ResponseEntity<Any> {
        return if (groupService.findByName(name) != null) {
            ResponseEntity.status(304).build()
        } else {
            val group = Group(null, name, course, emptyList())
            groupService.save(group)
            ResponseEntity.ok(GroupDTO(group))
        }
    }

    @GetMapping("/api/schedule/group")
    fun getAll(): ResponseEntity<List<GroupDTO>> {
        val groups = groupService.findAll()
        val response = mutableListOf<GroupDTO>()
        for (group in groups) {
            response.add(GroupDTO(group))
        }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/api/schedule/group/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Any> {
        val group = groupService.findById(id)
        return if (group != null) {
            ResponseEntity.ok(GroupDTO(group))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/api/schedule/group/{id}")
    fun updateGroup(@PathVariable id: Long, @RequestParam name: String?, @RequestParam course: Int?): ResponseEntity<Any> {
        val group = groupService.findById(id)
        return if (group != null) {
            group.name = name ?: group.name
            group.course = course ?: group.course
            groupService.save(group)
            ResponseEntity.ok(GroupDTO(group))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/api/schedule/group/{id}")
    fun deleteById(@PathVariable id: Long) {
        if (groupService.findById(id) == null) {
            return
        }
        groupService.deleteById(id)
    }

    @DeleteMapping("/api/schedule/group")
    fun deleteByName(@RequestParam name: String) {
        groupService.deleteByName(name)
    }
}