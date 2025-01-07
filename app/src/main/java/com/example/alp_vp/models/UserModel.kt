package com.example.alp_vp.models

data class UserResponse(
    val data: UserModel
)

data class UserModel (
    val id: Int,
    val username: String,
    val token: String?
)