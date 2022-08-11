package com.example.example

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "distList")
data class DistrictArray (

  @PrimaryKey(autoGenerate = true)
  @SerializedName("stateId")
  var stateId      : Int?    = null,

  @SerializedName("districtId")
  var districtId   : Int?    = null,

  @SerializedName("districtName")
  var districtName : String? = null

)