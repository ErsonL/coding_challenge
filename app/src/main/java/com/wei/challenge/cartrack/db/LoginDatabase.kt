package com.wei.challenge.cartrack.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wei.challenge.cartrack.model.Login
import com.wei.challenge.cartrack.utility.LOGIN_DB_NAME
import com.wei.challenge.cartrack.utility.LOGIN_DB_VERION
import com.wei.challenge.cartrack.utility.ioThread
import com.wei.challenge.cartrack.utility.runMD5Hash
import timber.log.Timber

@Database(entities = [Login::class], version = LOGIN_DB_VERION, exportSchema = false)
abstract class LoginDatabase : RoomDatabase() {

    abstract val loginDatabaseDao: LoginDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: LoginDatabase? = null

        val PREPOPULATE_DATA = Login("testUser1", runMD5Hash("abc#123"), "Singapore")
        val PREPOPULATE_DATA1 = Login("testUser2", runMD5Hash("cdf#789"), "Japan")
        val PREPOPULATE_DATA2 = Login("testUser3", runMD5Hash("sdd#789"), "Taiwan")


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

        fun destoryInstance() {
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
                    Timber.d("Insert prepopulate data")
                    db.loginDatabaseDao.insertLogin(PREPOPULATE_DATA)
                    db.loginDatabaseDao.insertLogin(PREPOPULATE_DATA1)
                    db.loginDatabaseDao.insertLogin(PREPOPULATE_DATA2)
                }
            }
        }

    }

}