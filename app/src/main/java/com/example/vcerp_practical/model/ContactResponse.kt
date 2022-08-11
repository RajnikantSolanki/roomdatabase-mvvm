package com.example.vcerp_practical.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "contactList")
class ContactResponse {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("contact_id")
    @Expose
    var id: Int ?= null

    @SerializedName("first_name")
    @Expose
    var firstName: String ?= null

    @SerializedName("last_name")
    @Expose
    var lastName: String ?= null

    @SerializedName("mobile_no")
    @Expose
    var mobile_no: String ?= null

    @SerializedName("email_id")
    @Expose
    var emailId: String ?= null


}