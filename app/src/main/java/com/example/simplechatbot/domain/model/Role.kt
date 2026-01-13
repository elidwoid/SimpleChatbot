package com.example.simplechatbot.domain.model

enum class Role {USER, MODEL}

data class ChatMessage(
    val role: Role,
    val text: String
)