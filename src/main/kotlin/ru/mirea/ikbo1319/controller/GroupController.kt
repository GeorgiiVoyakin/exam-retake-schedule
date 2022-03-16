package ru.mirea.ikbo1319.controller

import org.springframework.web.bind.annotation.*
import ru.mirea.ikbo1319.data.Group
import ru.mirea.ikbo1319.service.GroupService

@RestController
class GroupController(private val groupService: GroupService) {
    @PostMapping("/api/schedule/group")
    fun addGroup(@RequestParam name: String) {
        val group = Group(null, name, emptyList())
        groupService.save(group)
    }

    @GetMapping("/api/schedule/group")
    fun getAll(): MutableList<Group> {
        return groupService.findAll()
    }

    @DeleteMapping("/api/schedule/group/{id}")
    fun deleteById(@PathVariable id: Long) {
        groupService.deleteById(id)
    }

    @DeleteMapping("/api/schedule/group")
    fun deleteByName(@RequestParam name: String) {
        groupService.deleteByName(name)
    }
}