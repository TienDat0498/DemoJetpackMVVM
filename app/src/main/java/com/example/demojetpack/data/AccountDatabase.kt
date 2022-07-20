package com.example.demojetpack.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class AccountDatabase: RoomDatabase() {
    abstract val accountDao: AccountDao

    companion object {
        const val DATABASE_NAME = "account_database"
    }
}