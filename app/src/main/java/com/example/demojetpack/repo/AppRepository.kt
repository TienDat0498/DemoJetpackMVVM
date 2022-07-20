package com.example.demojetpack.repo

import com.example.demojetpack.data.Account

interface AppRepository {
    suspend fun registerAccount(account: Account)

    suspend fun checkAccount(id: String): Boolean

    suspend fun loginAccount(id: String, pass: String): Account?
}