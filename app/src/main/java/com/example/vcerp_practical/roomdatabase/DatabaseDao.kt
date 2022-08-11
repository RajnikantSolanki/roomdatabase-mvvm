package com.example.vcerp_practical.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.example.CityArray
import com.example.example.DistrictArray
import com.example.example.StatesArray
import com.example.vcerp_practical.model.ContactResponse

@Dao
interface DatabaseDao {

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertContact(storeResponse: ContactResponse)

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertState(statesArray: StatesArray)

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertCity(cityArray: CityArray)

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertDistrict(districtArray: DistrictArray)

    @Query("SELECT * from contactList")
    fun getContactList(): LiveData<List<ContactResponse>>


    @Query(value = "select * from contactList")
    fun allUsers() : List<ContactResponse>

    @Query("DELETE FROM contactList WHERE id =:contactId")
    fun deleteContact(contactId : Int)


}