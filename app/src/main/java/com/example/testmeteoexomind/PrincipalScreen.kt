package com.example.testmeteoexomind

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrincipalScreen(
    onButtonClicked: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bienvenue sur l'appli Meteo",
            modifier = Modifier.padding(16.dp),fontStyle = FontStyle.Italic,
            fontSize = 14.sp,
        )
        Button(onClick = onButtonClicked,
            colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        )) {
            Text(text = "Naviguer vers l'écran Metéo")

        }
    }

}