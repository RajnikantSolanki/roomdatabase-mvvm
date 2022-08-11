package com.example.example

import com.google.gson.annotations.SerializedName


data class CityResponse (

  @SerializedName("cityArray" )
  var cityArray : ArrayList<CityArray> = arrayListOf()

)