package com.example.example

import com.google.gson.annotations.SerializedName


data class DistResponse (

  @SerializedName("districtArray" )
  var districtArray : ArrayList<DistrictArray> = arrayListOf()

)