package com.example.testmeteoexomind.meteo

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.TextButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MeteoScreen(
    viewModel: MeteoViewModel = hiltViewModel(),
    navBack: () -> Unit,
) {

    val uiState by viewModel.uiState.collectAsState()
    val progress = uiState.loadingCurrentProgress / 100
    LaunchedEffect(Unit) {
        viewModel.initiateDataFetching()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = { navBack() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )) {
            Text(
                text = "Retour",
                modifier = Modifier.padding(16.dp),
            )
        }

        if(!uiState.isDone) {
            Text(
                text = "Chargement des données météorologiques ..",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(

                    modifier = Modifier
                        .border(2.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .drawWithContent {
                            with(drawContext.canvas.nativeCanvas) {
                                val checkPoint = saveLayer(null, null)

                                // Destination
                                drawContent()

                                // Source
                                drawRect(
                                    color = Color.Green,
                                    size = Size(size.width * (progress), size.height),
                                    blendMode = BlendMode.Xor
                                )
                                restoreToCount(checkPoint)
                            }
                        }
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "${(progress * 100).toInt()}%", color = Color.Gray, fontSize = 20.sp)
                }
            }


            Text(
                text = uiState.messageDattente,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }
        else {
            Button(
                modifier = Modifier.padding(16.dp),

                onClick = { navBack()},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text(
                    text = "Recommencer",
                    color = Color.Green
                )
            }
        }

        LazyVerticalGrid(columns = GridCells.Fixed(1)){
            itemsIndexed(uiState.listOfMeteoCity) { index, item ->
                if (index == 0) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
               Box(
                   modifier = Modifier
                       .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                       .fillMaxSize()
               ){
                   WeatherRow(item)
               }
            }
        }

    }

}

@Composable
fun WeatherRow(meteoCity: MeteoCity) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    )  {Column(modifier = Modifier.animateContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Text(
            "Ville: " + meteoCity.cityName,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(text = "Temperature: " + meteoCity.weather.main?.temp.toString() + "°C")
        Text(
            text = "Clouds: " + meteoCity.weather.clouds?.all.toString() + "%",
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
    }}



@Composable
fun DropdownExample(items: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Selected Item: ${items[selectedIndex]}",
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            TextButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Select Item")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                items.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        onClick = {
                            selectedIndex = index
                            expanded = false
                        }
                    ) {
                        Text(text = item)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

    }
}
