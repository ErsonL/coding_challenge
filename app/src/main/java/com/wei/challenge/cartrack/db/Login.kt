package com.wei.challenge.cartrack.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wei.challenge.cartrack.LOGIN_TABLE_NAME
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = LOGIN_TABLE_NAME)
data class Login(

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "password")
    var password: String,

    @ColumnInfo(name = "country")
    var country: String,

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
): Parcelable




