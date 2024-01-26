package chkan.example.weatherapiexample.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chkan.example.weatherapiexample.R
import chkan.example.weatherapiexample.domain.models.ForecastWeatherItem
import coil.compose.AsyncImage
import kotlin.random.Random

@Composable
fun WeatherForecastCard(
    forecast: ForecastWeatherItem,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = modifier
            .width(72.dp)
            .padding(horizontal = 4.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = forecast.date,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            WeatherImage(url = forecast.iconUrl)
            WeatherTitleText(stringResource(R.string.max_text))
            WeatherTempText(forecast.maxTempC)
            WeatherTitleText(stringResource(R.string.min_text))
            WeatherTempText(forecast.minTempC)
        }
    }
}

@Composable
private fun WeatherTitleText(text: String) {
    Text(
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 14.sp,
        modifier = Modifier
            .padding(top = 16.dp)
    )
}

@Composable
private fun WeatherTempText(temp: Double) {
    Text(
        text = stringResource(R.string.text_tempC,temp),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )
}

@Composable
fun WeatherImage(url: String) {
    val placeholder = rememberVectorPainter(
        image = Icons.Rounded.Place
    )
    AsyncImage(
        model = url,
        placeholder = placeholder,
        contentScale = ContentScale.Crop,
        contentDescription = "User photo",
        modifier = Modifier
            .size(48.dp)
            .padding(8.dp)
            .aspectRatio(1f / 1f)
            .clip(CircleShape)
    )
}

private class ForecastPreviewProvider : PreviewParameterProvider<ForecastWeatherItem> {
    override val values: Sequence<ForecastWeatherItem> = sequenceOf(
        ForecastWeatherItem(
            id = Random.nextInt(),
            date = "Jan",
            maxTempC = 4.5,
            minTempC = 6.7,
            iconUrl = ""
        )
    )
}

@Preview
@Composable
fun WeatherForecastCardPreview(
    @PreviewParameter(ForecastPreviewProvider::class) forecast: ForecastWeatherItem
) {
    WeatherForecastCard(forecast = forecast)
}