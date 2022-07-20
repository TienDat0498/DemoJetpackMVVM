package com.example.demojetpack.repo

import com.example.demojetpack.data.Account
import com.example.demojetpack.data.AccountDatabase
import javax.inject.Inject

class AppRepositoryImp(
    private val database: AccountDatabase
): AppRepository {
    override suspend fun registerAccount(account: Account) {
        return database.accountDao.registerAccount(account)
    }

    override suspend fun checkAccount(id: String): Boolean {
        return database.accountDao.isAccountIsExist(id)
    }

    override suspend fun loginAccount(id: String, pass: String): Account? {
        return database.accountDao.login(id, pass)
    }

}