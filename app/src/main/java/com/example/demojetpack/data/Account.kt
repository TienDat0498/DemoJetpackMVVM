package com.example.demojetpack.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Account(
    @PrimaryKey
    val id: String,
    val password: String,
    val name: String,
    val phone: String,
) : Parcelable