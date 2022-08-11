package com.example.vcerp_practical.Repository


import com.example.example.CityResponse
import com.example.example.DistResponse
import com.example.example.StateResponse
import com.example.vcerp_practical.API.Apis
import com.example.vcerp_practical.API.UsesCaseResult
import com.example.vcerp_practical.model.ContactResponse


interface AddressRepository {
    suspend fun getState(): UsesCaseResult<StateResponse>
    suspend fun getCity(): UsesCaseResult<CityResponse>
    suspend fun getDist(): UsesCaseResult<DistResponse>
}

class AddressRepositoryImpl(private val apis: Apis): AddressRepository {

    override suspend fun getState(): UsesCaseResult<StateResponse> {
        val result = apis.getState().await()
        return try {
            UsesCaseResult.Success(result)
        }catch (e: Exception){
            UsesCaseResult.Failed(e)
        }
    }

    override suspend fun getCity(): UsesCaseResult<CityResponse> {
        val result = apis.getCity().await()
        return try {
            UsesCaseResult.Success(result)
        }catch (e: Exception){
            UsesCaseResult.Failed(e)
        }
    }

    override suspend fun getDist(): UsesCaseResult<DistResponse> {
        val result = apis.getDist().await()
        return try {
            UsesCaseResult.Success(result)
        }catch (e: Exception){
            UsesCaseResult.Failed(e)
        }
    }

}
