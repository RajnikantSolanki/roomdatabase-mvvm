package com.example.example

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cityList")
data class CityArray (

  @PrimaryKey(autoGenerate = true)
  @SerializedName("districtId")
  var districtId : Int?    = null,

  @SerializedName("cityId")
  var cityId : Int?  = null,

  @SerializedName("cityName")
  var cityName : String? = null

)