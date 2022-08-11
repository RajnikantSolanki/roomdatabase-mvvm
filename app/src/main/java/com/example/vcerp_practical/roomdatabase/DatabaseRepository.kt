package com.example.vcerp_practical.roomdatabase

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.example.CityArray
import com.example.example.DistrictArray
import com.example.example.StatesArray
import com.example.vcerp_practical.model.ContactResponse

class DatabaseRepository(private val databaseDao: DatabaseDao) {

    val contactList:LiveData<List<ContactResponse>> = databaseDao.getContactList()

    @WorkerThread
    fun insertContact(contactResponse: ContactResponse){
        databaseDao.insertContact(contactResponse)
    }

    @WorkerThread
    fun insertState(statesArray: StatesArray){
        databaseDao.insertState(statesArray)
    }

    @WorkerThread
    fun insertCity(cityArray: CityArray){
        databaseDao.insertCity(cityArray)
    }

    @WorkerThread
    fun insertDistrict(districtArray: DistrictArray){
        databaseDao.insertDistrict(districtArray)
    }

    @WorkerThread
    fun deleteContact(id:Int) {
        databaseDao.deleteContact(id)
    }
}