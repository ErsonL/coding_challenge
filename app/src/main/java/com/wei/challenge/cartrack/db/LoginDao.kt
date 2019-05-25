package com.wei.challenge.cartrack.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wei.challenge.cartrack.model.Login
import com.wei.challenge.cartrack.utility.LOGIN_TABLE_NAME

@Dao
interface LoginDao {
    @Query("select * from $LOGIN_TABLE_NAME ORDER BY id DESC")
    fun getAllLogin(): List<Login>

    @Query("SELECT * from $LOGIN_TABLE_NAME WHERE name = :name AND password = :password AND country = :country")
    fun getLogin(name: String, password: String, country: String): Login?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLogin(data: Login)

    @Query("delete from $LOGIN_TABLE_NAME")
    fun deleteAllData()
}