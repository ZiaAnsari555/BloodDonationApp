package com.yodeck.models.getscreen

data class GetScreenResponse(
    val data: Data,
    val error: String,
    val message: String,
    val status: Boolean
)