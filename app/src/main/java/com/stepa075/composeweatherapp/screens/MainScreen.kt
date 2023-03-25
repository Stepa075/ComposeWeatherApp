package com.stepa075.composeweatherapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.stepa075.composeweatherapp.R
import com.stepa075.composeweatherapp.data.WeatherModel
import com.stepa075.composeweatherapp.ui.theme.BlueLight
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun MainCard() {

    Column(
        modifier = Modifier
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "im3",
                            tint = Color.White
                        )

                    }
                    Text(
                        text = "23°C/12°C",
                        style = TextStyle(fontSize = 15.sp),
                        color = Color.White
                    )

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sync),
                            contentDescription = "im4",
                            tint = Color.White
                        )

                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout() {
    val tablist = listOf("HOURS", "DAYS")
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp)
            .clip(RoundedCornerShape(5.dp))
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { pos ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, pos)
                )
            },
            backgroundColor = BlueLight,
            contentColor = Color.White
        ) {
            tablist.forEachIndexed { index, text ->
                Tab(selected = false,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(text = text) }
                )
            }
        }
        HorizontalPager(
            count = tablist.size,
            state = pagerState,
            modifier = Modifier.weight(1.0f)
        ) { index ->
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(
                    listOf(
                        WeatherModel(
                            "London",
                            "10:00",
                            "25°C",
                            "Sunny",
                            "//cdn.weatherapi.com/weather/64x64/day/116.png",
                            "",
                            "",
                            ""
                        ),
                        WeatherModel(
                            "London",
                            "25.03.2023",
                            "25°C",
                            "Sunny",
                            "//cdn.weatherapi.com/weather/64x64/day/116.png",
                            "26°C",
                            "12°C",
                            "hjhkjhhkhkhkjh"
                        )
                    )
                ) { _, item ->
                    ListItem(item)

                }

            }
        }
    }
}