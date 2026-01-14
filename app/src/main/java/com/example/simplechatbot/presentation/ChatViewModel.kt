package com.example.simplechatbot.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplechatbot.domain.model.ChatMessage
import com.example.simplechatbot.domain.model.Role
import com.example.simplechatbot.domain.usecase.SendMessageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    private val sendMessage: SendMessageUseCase
): ViewModel() {

    private val _state = MutableStateFlow(ChatUiState())
    val state: StateFlow<ChatUiState> = _state


    fun onSend(text: String){
        val  msg = text.trim() //removes white spaces

        if(msg.isEmpty()) return

        val current = _state.value.messages
        _state.value = _state.value.copy(
            messages = current + ChatMessage(Role.USER,msg
        ),
            isLoading = true,
            error = null
        )

        viewModelScope.launch {
            try {
                val reply = sendMessage(msg)

                _state.value = _state.value.copy(
                    messages = _state.value.messages +
                            ChatMessage(Role.MODEL,reply
                ),
                    isLoading = false

                )
            } catch (e: Exception){
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Something went wrong"
                )
            }
        }
    }
}