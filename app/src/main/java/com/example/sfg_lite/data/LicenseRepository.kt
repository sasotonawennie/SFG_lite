package com.example.sfg_lite.data

import android.content.Context
import android.provider.Settings
import com.example.sfg_lite.network.LicenseApi
import com.example.sfg_lite.network.LicenseRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.GsonConverterFactory

enum class LicenseState {
    ACTIVE,
    TRIAL_ACTIVE,
    TRIAL_ACTIVE_DAY_6,
    TRIAL_STARTED,
    EXPIRED,
    ERROR
}

class LicenseRepository(private val context: Context) {

    private val api: LicenseApi by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .followRedirects(true)
            .followSslRedirects(true)
            .build()

        Retrofit.Builder()
            .baseUrl("https://script.google.com/")
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LicenseApi::class.java)
    }

    fun getSSAID(): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID) ?: "UNKNOWN"
    }

    suspend fun checkLicense(): LicenseState {
        return try {
            val response = api.verifyLicense(LicenseRequest(getSSAID()))
            if (response.isSuccessful) {
                when (response.body()?.trim()) {
                    "ACTIVE" -> LicenseState.ACTIVE
                    "TRIAL_ACTIVE" -> LicenseState.TRIAL_ACTIVE
                    "TRIAL_ACTIVE_DAY_6" -> LicenseState.TRIAL_ACTIVE_DAY_6
                    "TRIAL_STARTED" -> LicenseState.TRIAL_STARTED
                    "EXPIRED" -> LicenseState.EXPIRED
                    else -> LicenseState.ERROR
                }
            } else {
                LicenseState.ERROR
            }
        } catch (e: Exception) {
            LicenseState.ERROR
        }
    }
}
