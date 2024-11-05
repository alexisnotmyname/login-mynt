package com.alexc.ph.data.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeLoginRepository @Inject constructor(): LoginRepository {

    override suspend fun login(userName: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            //login api call here
            Log.d("FakeLoginRepository","Calling API service....")
            delay(1000)
            return@withContext true
        }
    }
 }