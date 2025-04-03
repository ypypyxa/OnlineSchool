package com.pivnoydevelopment.common.data.network

import com.pivnoydevelopment.common.data.network.dto.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}