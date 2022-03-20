package ru.mirea.ikbo1319.dto

import ru.mirea.ikbo1319.data.Group

data class GroupDTO(var id: Long, var name: String, var course: Int) {
    constructor(group: Group) : this(group.id!!, group.name, group.course)
}
