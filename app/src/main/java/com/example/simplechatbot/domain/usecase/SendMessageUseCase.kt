package com.example.simplechatbot.domain.usecase

import com.example.simplechatbot.domain.repository.ChatRepository

class SendMessageUseCase(
    private val  repository: ChatRepository
) {
    suspend operator fun invoke(text: String): String{
        return repository.sendMessage(text)
    }
}