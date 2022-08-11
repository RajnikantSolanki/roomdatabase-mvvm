package com.example.vcerp_practical.roomdatabase

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.example.CityArray
import com.example.example.DistrictArray
import com.example.example.StatesArray
import com.example.vcerp_practical.model.ContactResponse

@Database(entities = arrayOf(ContactResponse::class,CityArray::class,StatesArray::class,DistrictArray::class), autoMigrations = [AutoMigration(from = 1, to = 2)], version = 1, exportSchema = false)
abstract class AppRoomDataBase : RoomDatabase() {

abstract fun databaseDao(): DatabaseDao

companion object{

    @Volatile
    private var INSTANCE : AppRoomDataBase ?= null

    fun getDatabase(context : Context): AppRoomDataBase{
        val tempInstance = INSTANCE
        if (tempInstance != null) {
            return tempInstance
        }
        synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppRoomDataBase::class.java,
                "contact_db"
            ).build()
            INSTANCE = instance
            return instance
        }
    }

}

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }
}