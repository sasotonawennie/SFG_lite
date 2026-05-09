package com.example.sfg_lite.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class LicenseRequest(val ssaid: String)

interface LicenseApi {
    @POST("macros/s/AKfycbxlPq7AMDhvVTz1MMecRkF06vexzPVQ93pYUAmdXg4kKmbf13yZOmxZqeukuuEyHcP0/exec")
    suspend fun verifyLicense(@Body request: LicenseRequest): Response<String>
}
