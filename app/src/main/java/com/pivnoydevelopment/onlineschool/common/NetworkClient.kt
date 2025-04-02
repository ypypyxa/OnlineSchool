package com.pivnoydevelopment.onlineschool.common

import com.pivnoydevelopment.onlineschool.common.data.dto.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}