package com.yodeck.models

import com.yodeck.models.Data

data class SessionResponse(
    val `data`: Data,
    val message: String,
    val status: Boolean
)