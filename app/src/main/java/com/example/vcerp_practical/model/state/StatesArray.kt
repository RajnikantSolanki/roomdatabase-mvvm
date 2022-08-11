package com.example.example

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "stateList")
data class StatesArray (

  @PrimaryKey(autoGenerate = true)
  @SerializedName("stateId")
  var stateId  : Int?  = null,

  @SerializedName("stateName")
  var stateName : String? = null
)