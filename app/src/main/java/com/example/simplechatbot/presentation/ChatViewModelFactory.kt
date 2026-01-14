package com.example.simplechatbot.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplechatbot.data.remote.GeminiDataSource
import com.example.simplechatbot.data.repository.ChatRepositoryImpl
import com.example.simplechatbot.domain.usecase.SendMessageUseCase

class ChatViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dataSource = GeminiDataSource()
        val repo = ChatRepositoryImpl(dataSource)
        val useCase = SendMessageUseCase(repo)
        @Suppress("UNCHECKED_CAST")
        return ChatViewModel(useCase) as T
    }
}