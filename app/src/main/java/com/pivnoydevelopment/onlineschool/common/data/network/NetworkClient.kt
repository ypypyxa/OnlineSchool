package com.pivnoydevelopment.onlineschool.common.data.network

import com.pivnoydevelopment.onlineschool.common.data.network.dto.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}