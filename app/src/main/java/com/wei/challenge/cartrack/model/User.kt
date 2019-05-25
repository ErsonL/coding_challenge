package com.wei.challenge.cartrack.model


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.wei.challenge.cartrack.utility.USER_TABLE_NAME


@Entity(tableName = USER_TABLE_NAME)
data class User(
    @PrimaryKey
    val id: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("username")
    val username: String,
    val email: String,
    @Embedded(prefix = "address_")
    @field:SerializedName("address")
    val address: Address,
    val phone: String,
    val website: String,
    @Embedded(prefix = "company_")
    @field:SerializedName("company")
    val company: Company){

    data class Address(
        @field:SerializedName("street")
        val street: String,
        val suite: String,
        val city: String,
        val zipcode: String,
        @Embedded(prefix = "geo_")
        val geo: Geo
    )



    data class Company(
        @field:SerializedName("name")
        val name: String,
        val catchPhrase: String,
        val bs: String){
        override fun toString(): String {
            return "$name, $catchPhrase, $bs"
        }
    }


    data class Geo(
        val lat: String = "",
        val lng: String = "")

    companion object {
        const val UNKNOWN_ID = -1
    }
    override fun toString(): String {
        return "id = $id name:$name"
    }
}

