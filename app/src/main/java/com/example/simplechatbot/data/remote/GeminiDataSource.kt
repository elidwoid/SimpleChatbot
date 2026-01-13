package com.example.simplechatbot.data.remote

import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend

class GeminiDataSource {

    private val model = Firebase.ai(backend = GenerativeBackend.googleAI())
        .generativeModel("gemini-2.5-flash-lite")

    private val chat = model.startChat()

    suspend fun send(text: String): String{
        val response = chat.sendMessage(text)
        return response.text ?: "(no response)"
    }
}