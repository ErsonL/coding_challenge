package com.wei.challenge.cartrack.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wei.challenge.cartrack.LOGIN_DB_NAME
import com.wei.challenge.cartrack.LOGIN_DB_VERION
import com.wei.challenge.cartrack.utility.ioThread

@Database(entities = [Login::class], version = LOGIN_DB_VERION, exportSchema = false)
abstract class LoginDatabase : RoomDatabase() {

    abstract val loginDatabaseDao: LoginDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: LoginDatabase? = null

        val PREPOPULATE_DATA = Login("testUser1", "abc#123", "Singapore")
        val PREPOPULATE_DATA1 = Login("testUser2", "cdf#789", "Japan")
        val PREPOPULATE_DATA2 = Login("testUser3", "sdd#789", "Taiwan")


        fun getInstance(context: Context): LoginDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = buildDatabase(context)
                    INSTANCE = instance
                }
                populateInitialData(instance)
                return instance
            }
        }

        private fun destoryInstance() {

            INSTANCE = null
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                LoginDatabase::class.java,
                LOGIN_DB_NAME
            ).build()

        private fun populateInitialData(db: LoginDatabase) {
            ioThread {
                if (db.loginDatabaseDao.getAllLogin().isEmpty()) {
                    db.loginDatabaseDao.insertLogin(PREPOPULATE_DATA)
                    db.loginDatabaseDao.insertLogin(PREPOPULATE_DATA1)
                    db.loginDatabaseDao.insertLogin(PREPOPULATE_DATA2)
                }
            }
        }

    }

}