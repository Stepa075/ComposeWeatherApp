package com.stepa075.composeweatherapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.stepa075.composeweatherapp.R
import com.stepa075.composeweatherapp.ui.theme.BlueLight

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    Image(
        painter = painterResource(id = R.drawable.weather),
        contentDescription = "im1",
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.5f),
        contentScale = ContentScale.FillBounds,
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = BlueLight,
            elevation = 0.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                        text = "24 Mar 2023 12:00",
                        style = TextStyle(fontSize = 15.sp),
                        color = Color.White
                    )
                    AsyncImage(
                        modifier = Modifier
                            .size(35.dp)
                            .padding(top = 3.dp, end = 8.dp),
                        model = "https://cdn.weatherapi.com/weather/64x64/day/353.png",
                        contentDescription = "in2"
                    )
                }

                Text(
                    text = "Kharkov",
                    style = TextStyle(fontSize = 32.sp),
                    color = Color.White
                )
                Text(
                    text = "23°C",
                    style = TextStyle(fontSize = 64.sp),
                    color = Color.White
                )
                Text(
                    text = "Sunny",
                    style = TextStyle(fontSize = 15.sp),
                    color = Color.White
                )
                Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = "im3",
                            tint = Color.White)
                        
                    }
                    Text(
                        text = "23°C/12°C",
                        style = TextStyle(fontSize = 15.sp),
                        color = Color.White
                    )

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.ic_sync), contentDescription = "im4",
                        tint = Color.White)

                    }
                    
                }
            }
        }
    }
}