package com.wei.challenge.cartrack.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wei.challenge.cartrack.model.Login
import com.wei.challenge.cartrack.model.User
import com.wei.challenge.cartrack.utility.LOGIN_DB_NAME
import com.wei.challenge.cartrack.utility.LOGIN_DB_VERSION
import com.wei.challenge.cartrack.utility.ioThread
import com.wei.challenge.cartrack.utility.runMD5Hash
import timber.log.Timber



@Database(
    entities = [
        Login::class,
        User::class],
    version = LOGIN_DB_VERSION,
    exportSchema = false)

abstract class AppDatabase : RoomDatabase() {

    abstract val loginDao: LoginDao
    abstract val userDao: UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        val PREPOPULATE_DATA = Login("testUser1", runMD5Hash("abc#123"), "Singapore")
        val PREPOPULATE_DATA1 = Login("testUser2", runMD5Hash("cdf#789"), "Japan")
        val PREPOPULATE_DATA2 = Login("testUser3", runMD5Hash("sdd#789"), "Taiwan")


        fun getInstance(context: Context): AppDatabase {
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
                AppDatabase::class.java,
                LOGIN_DB_NAME
            ).build()

        private fun populateInitialData(db: AppDatabase) {
            ioThread {
                if (db.loginDao.getAllLogin().isEmpty()) {
                    Timber.d("Insert prepopulate data")
                    db.loginDao.insertLogin(PREPOPULATE_DATA)
                    db.loginDao.insertLogin(PREPOPULATE_DATA1)
                    db.loginDao.insertLogin(PREPOPULATE_DATA2)
                }
            }
        }

    }

}