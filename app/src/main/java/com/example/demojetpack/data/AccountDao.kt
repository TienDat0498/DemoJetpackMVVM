package com.example.demojetpack.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun registerAccount(account: Account)

    @Query("SELECT EXISTS(SELECT * FROM account WHERE id = :acc)")
    suspend fun isAccountIsExist(acc : String) : Boolean

    @Query("SELECT * FROM account WHERE id = :acc AND password= :pass LIMIT 1")
    suspend fun login(acc: String, pass: String): Account?
}
