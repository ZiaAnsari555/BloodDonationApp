package com.yodeck.models

data class Data(
    val access_token: String, // use for access every api.. main access token of app
    val request_token: String,
    val user_token: String,
    val otp: String,
    val sent_to: String,
    val to: String, // the recipient to which otp will be sent
    val otp_for: Int,
    val expiration_time: String,
    val password_reset_token: String, // will be used in change password params
)