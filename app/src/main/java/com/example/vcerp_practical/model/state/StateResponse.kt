package com.example.example

import com.google.gson.annotations.SerializedName


data class StateResponse (

  @SerializedName("statesArray")
  var statesArray : ArrayList<StatesArray> = arrayListOf()

)