package com.wei.challenge.cartrack.model


data class User(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company) {
    override fun toString(): String {
        return "id = $id name:$name"
    }
}


data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo){
    override fun toString(): String {
        return "$street, $suite, $city, $zipcode"
    }
}

data class Company(
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