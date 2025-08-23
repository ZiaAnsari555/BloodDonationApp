package com.yodeck.models

data class AddDonorResponse(
    val `data`: DataX,
    val success: Boolean,
    val error: String
)