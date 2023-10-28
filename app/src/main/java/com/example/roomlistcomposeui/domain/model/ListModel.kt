package com.example.roomlistcomposeui.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val age: String,
    val name: String,
    val gender: Int,
    val surname: String,
    val description: String
)
