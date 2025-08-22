package com.yodeck.models

data class GetDeviceResponse(
    val message: String,
    val status: Boolean,
    val data: GetDevice? = null
)


data class GetDevice(
    val device_code: String? = null,
)