package chkan.example.weatherapiexample.ui.screens.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chkan.example.weatherapiexample.R
import chkan.example.weatherapiexample.domain.models.CurrentWeatherItem
import coil.compose.AsyncImage

@Composable
fun WeatherListCard(
    weather: CurrentWeatherItem,
    onWeatherClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple()
        ){
                onWeatherClicked()
        }
    ) {
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(
                text = weather.nameCity,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp, end = 8.dp)
                    .weight(8f)
            )
            WeatherImage(url = weather.iconUrl)
            Text(
                text = stringResource(R.string.text_tempC,weather.currentTempC),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 24.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 16.dp)

            )
        }
    }
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

private class WeatherListPreviewProvider : PreviewParameterProvider<CurrentWeatherItem> {
    override val values: Sequence<CurrentWeatherItem> = sequenceOf(
        CurrentWeatherItem(id = 1, iconUrl = "", nameCity = "Gandalf", currentTempC = 10.0),
        CurrentWeatherItem(id = 1, iconUrl = "", nameCity = "Gandalf long long long long", currentTempC = 17.0)
    )
}

@Preview
@Composable
fun WeatherListCardPreview(
    @PreviewParameter(WeatherListPreviewProvider::class) weather: CurrentWeatherItem
) {
    WeatherListCard(weather = weather, onWeatherClicked = {})
}