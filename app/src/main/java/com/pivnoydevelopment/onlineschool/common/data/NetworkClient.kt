package com.pivnoydevelopment.onlineschool.common.data

import com.pivnoydevelopment.onlineschool.common.data.dto.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}