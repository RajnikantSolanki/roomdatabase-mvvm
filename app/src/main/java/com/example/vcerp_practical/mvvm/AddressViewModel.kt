package com.example.vcerp_practical.mvvm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.CityResponse
import com.example.example.DistResponse
import com.example.example.StateResponse
import com.example.vcerp_practical.API.UsesCaseResult
import com.example.vcerp_practical.ApplicationClass
import com.example.vcerp_practical.Repository.AddressRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class AddressViewModel(private val addressRepository: AddressRepository): ViewModel(),
    CoroutineScope {

    val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showloding = MutableLiveData<Boolean>()
    val stateResponse = MutableLiveData<StateResponse>()
    val cityResponse = MutableLiveData<CityResponse>()
    val distResponse = MutableLiveData<DistResponse>()
    val showerror = MutableLiveData<String>()


    private val context : Context = ApplicationClass.mInstance!!.applicationContext


    fun doState() {
        launch {
            val result = withContext(Dispatchers.IO) {
                addressRepository.getState()
            }
            when(result){
                is UsesCaseResult.Success -> {
                    withContext(Dispatchers.Main){
                        stateResponse.value= result.data
                    }
                }
                is UsesCaseResult.Failed -> {
                    showerror.value = result.exception.message
                }
            }
        }
    }

    fun doCity() {
        launch {
            val result = withContext(Dispatchers.IO) {
                addressRepository.getCity()
            }
            showloding.value = false
            when(result){
                is UsesCaseResult.Success -> {
                    withContext(Dispatchers.Main){
                        cityResponse.value= result.data
                    }
                }
                is UsesCaseResult.Failed -> {
                    showerror.value = result.exception.message
                }
            }
        }
    }

    fun doDist() {
        launch {
            val result = withContext(Dispatchers.IO) {
                addressRepository.getDist()
            }
            showloding.value = false
            when(result){
                is UsesCaseResult.Success -> {
                    withContext(Dispatchers.Main){
                        distResponse.value= result.data
                    }
                }
                is UsesCaseResult.Failed -> {
                    showerror.value = result.exception.message
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}