package com.example.simplechatbot.domain.repository

import com.example.simplechatbot.domain.model.ChatMessage

interface ChatRepository {

    suspend fun sendMessage(userText: String): String
    fun  getHistory(): List<ChatMessage>
}