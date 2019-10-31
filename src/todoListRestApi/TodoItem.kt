package com.notepoint.todoListRestApi

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.time.LocalDate
import java.time.LocalDateTime

data class TodoItem(
    val id:Int,
    val title: String,
    val details: String,

    @JsonSerialize(using = ToStringSerializer::class)
    val dueDate: LocalDate,

    val importance: Importance
)

enum class Importance {
    LOW, MEDIUM, HIGH
}