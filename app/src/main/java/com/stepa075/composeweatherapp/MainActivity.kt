package com.stepa075.composeweatherapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.stepa075.composeweatherapp.data.WeatherModel
import com.stepa075.composeweatherapp.screens.MainCard

import com.stepa075.composeweatherapp.screens.TabLayout
import com.stepa075.composeweatherapp.ui.theme.ComposeWeatherAppTheme
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWeatherAppTheme {

                val dayList = remember{
                    mutableStateOf(listOf<WeatherModel>())
                }

                val currentDay = remember{
                    mutableStateOf(WeatherModel(
                        "","","0.0","","","0.0","0.0",""
                    )
                    )
                }
                getData("London", this, dayList, currentDay)
                Image(
                    painter = painterResource(id = R.drawable.weather),
                    contentDescription = "im1",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.5f),
                    contentScale = ContentScale.FillBounds,
                )
              Column {
                  MainCard(currentDay)
                  TabLayout(dayList)
              }


            }
        }
    }
}

private fun getData(city: String, context: Context,
                    dayList: MutableState<List<WeatherModel>>,
                    currentDay: MutableState<WeatherModel>){
    val api = "38b27a1546ab4bb5a9364709232202"
    val url = "https://api.weatherapi.com/v1/forecast.json?key=$api&q=$city&days=3&aqi=no&alerts=no"
    val queue = Volley.newRequestQueue(context)
    val sRequest = StringRequest(
        Request.Method.GET,
        url,{
            response ->
            val list = getWeatherByDays(response)
            dayList.value = list
            currentDay.value = list[0]
        },
        { Log.d("MyLog", "Error request: $it")}

    )
    queue.add(sRequest)
}

private fun getWeatherByDays(response: String): List<WeatherModel>{
    if(response.isEmpty()) return listOf()
    val list = ArrayList<WeatherModel>()
    val mainObject = JSONObject(response)
    val city = mainObject.getJSONObject("location").getString("name")
    val days = mainObject.getJSONObject("forecast").getJSONArray("forecastday")

    for(i in 0 until days.length()){
        val item = days[i] as JSONObject
        list.add(
            WeatherModel(
                city,
                item.getString("date"),
                "",
                item.getJSONObject("day").getJSONObject("condition").getString("text"),
                item.getJSONObject("day").getJSONObject("condition").getString("icon"),
                item.getJSONObject("day").getString("maxtemp_c"),
                item.getJSONObject("day").getString("mintemp_c"),
                item.getJSONArray("hour").toString()
            )
        )
    }
    list[0] = list[0].copy(
        time = mainObject.getJSONObject("current").getString("last_updated"),
        currentTemp = mainObject.getJSONObject("current").getString("temp_c")

    )
    return list
}