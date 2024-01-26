package chkan.example.weatherapiexample.ui.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chkan.example.weatherapiexample.R
import chkan.example.weatherapiexample.domain.models.ForecastWeatherItem


@Composable
fun DetailsScreen(
    city: String,
    onNavigateBack: () -> Unit,
    forecastList: List<ForecastWeatherItem>
) {
    Column {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = stringResource(id = R.string.material_icon),
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp)
                .size(36.dp)
                .clickable {
                    onNavigateBack.invoke()
                }
        )
        Text(
            text = city,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            modifier = Modifier
                .padding(top = 8.dp, start = 32.dp)
        )
        ForecastList(forecastList)
    }
}

@Composable
fun ForecastList(list: List<ForecastWeatherItem>) {
    Box {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(16.dp)
        )
        {
            items(list, key = { item -> item.id }) { item ->
                WeatherForecastCard(
                    forecast = item
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(city = "City", onNavigateBack = {}, forecastList = listOf(ForecastWeatherItem(
        id = 1,
        date = "Feb.24",
        maxTempC = 4.5,
        minTempC = 6.7,
        iconUrl = ""
    )) )
}
