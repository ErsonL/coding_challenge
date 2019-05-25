package com.wei.challenge.cartrack.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wei.challenge.cartrack.model.User
import com.wei.challenge.cartrack.utility.USER_TABLE_NAME

@Dao
interface UserDao {
    @Query("select * from $USER_TABLE_NAME ORDER BY id DESC")
    fun getAllUser(): List<User>

    @Query("SELECT * from $USER_TABLE_NAME WHERE name = :name")
    fun getUser(name: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUsers(repositories: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(data: User)

    @Query("delete from $USER_TABLE_NAME")
    fun deleteAllData()
}