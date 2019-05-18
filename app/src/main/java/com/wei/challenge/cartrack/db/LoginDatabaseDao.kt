package com.wei.challenge.cartrack.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wei.challenge.cartrack.LOGIN_TABLE_NAME

@Dao
interface LoginDatabaseDao {
    @Query("select * from $LOGIN_TABLE_NAME ORDER BY id DESC")
    fun getAllLogin(): List<Login>

    @Query("SELECT * from $LOGIN_TABLE_NAME WHERE name = :name AND password = :password")
    fun getLogin(name: String, password: String): Login?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLogin(data: Login)

    @Query("delete from $LOGIN_TABLE_NAME")
    fun deleteAllData()
}