package com.example.simplechatbot.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplechatbot.domain.model.Role
import com.example.simplechatbot.presentation.ChatViewModel
import com.example.simplechatbot.presentation.ChatViewModelFactory


@Composable
fun  ChatScreen(
    modifier: Modifier = Modifier,
    vm: ChatViewModel = viewModel(factory = ChatViewModelFactory())
){
    val  state by vm.state.collectAsState()
    var input by remember { mutableStateOf("") }

    Column(modifier = modifier
        .fillMaxWidth()
    ) {
        AppHeader()


        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.messages) { m ->
                val alignEnd = m.role == Role.USER
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (alignEnd) Arrangement.End else Arrangement.Start
                ){
                    Surface(
                        shape = MaterialTheme.shapes.medium){
                            Text(
                                m.text,
                                modifier = Modifier.padding(12.dp))
                        }
                }
            }
        }


        if (state.isLoading) {
            Text(
                "Typing…",
                modifier = Modifier.padding(start = 16.dp, bottom = 6.dp))
        }
        state.error?.let {
            Text(
                "Error: $it",
                modifier = Modifier.padding(16.dp)) }


        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = input,
                onValueChange = { input = it },
                placeholder = { Text("Type…") }
            )
            IconButton(onClick = {
                vm.onSend(input)
                input = ""
            }) {
                Icon(
                    Icons.Default.Send,
                    contentDescription = "Send")
            }

        }

    }
}



@Composable
fun AppHeader(){
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ){
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Chattie",
            color = Color.White,
            fontSize = 22.sp,
            fontStyle = FontStyle.Italic
        )
    }
}