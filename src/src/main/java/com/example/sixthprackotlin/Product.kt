package com.example.sixthprackotlin;

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double
);