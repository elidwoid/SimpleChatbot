package com.example.simplechatbot.data.repository

import com.example.simplechatbot.data.remote.GeminiDataSource
import com.example.simplechatbot.domain.model.ChatMessage
import com.example.simplechatbot.domain.model.Role
import com.example.simplechatbot.domain.repository.ChatRepository

class ChatRepositoryImpl(
    private val dataSource: GeminiDataSource
): ChatRepository {

    private val  history = mutableListOf<ChatMessage>()

    override suspend fun sendMessage( userText: String): String {
        history += ChatMessage(Role.USER,userText)

        val reply = dataSource.send(userText)

        history += ChatMessage(Role.MODEL,reply)
        return reply
    }

    override fun getHistory(): List<ChatMessage> = history.toList()
}