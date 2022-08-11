package com.example.vcerp_practical.roomdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.example.CityArray
import com.example.example.DistrictArray
import com.example.example.StateResponse
import com.example.example.StatesArray
import com.example.vcerp_practical.model.ContactResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseViewModel(application: Application) : AndroidViewModel(application) {

    private var repository :DatabaseRepository ?= null
    var allContactList : LiveData<List<ContactResponse>>?= null

    init {
        val databaseDao = AppRoomDataBase.getDatabase(application).databaseDao()
        repository = DatabaseRepository(databaseDao)
        allContactList=repository!!.contactList
    }

    fun insertState(statesArray: StatesArray) = viewModelScope.launch(Dispatchers.IO) {
        repository!!.insertState(statesArray)
    }

    fun insertCity(cityArray: CityArray) = viewModelScope.launch(Dispatchers.IO) {
        repository!!.insertCity(cityArray)
    }

    fun insertDist(districtArray: DistrictArray) = viewModelScope.launch(Dispatchers.IO) {
        repository!!.insertDistrict(districtArray)
    }

    fun insertContact(contactResponse: ContactResponse) {
        repository!!.insertContact(contactResponse)
    }

    fun deleteContact(id:Int) {
        repository!!.deleteContact(id)
    }

}