package com.alexc.ph.data.repository

interface LoginRepository {
    suspend fun login(userName: String, password: String): Boolean
}