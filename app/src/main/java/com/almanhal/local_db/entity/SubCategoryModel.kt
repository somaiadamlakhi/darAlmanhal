package com.almanhal.local_db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubCategoryModel(
    @PrimaryKey
    val id: String,
    val categoryName: String
)