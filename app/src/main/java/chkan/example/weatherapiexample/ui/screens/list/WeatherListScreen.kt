package chkan.example.weatherapiexample.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chkan.example.weatherapiexample.R
import chkan.example.weatherapiexample.core.model.ErrorResult
import chkan.example.weatherapiexample.core.model.PendingResult
import chkan.example.weatherapiexample.core.model.SuccessResult
import chkan.example.weatherapiexample.domain.models.CurrentWeatherItem
import chkan.example.weatherapiexample.ui.ShareViewModel
import chkan.example.weatherapiexample.ui.models.WeatherUiModel

@Composable
fun WeatherListScreen(
    onNavigateToDetails: (String) -> Unit,
    shareViewModel: ShareViewModel
) {
    val resultFromApi by shareViewModel.citiesWithWeatherResource

    when (resultFromApi) {
        is SuccessResult -> WeatherList((resultFromApi as SuccessResult<WeatherUiModel>).data.currentListItem, onNavigateToDetails)
        is ErrorResult -> ShowError((resultFromApi as ErrorResult<WeatherUiModel>).exception){ shareViewModel.tryAgain() }
        is PendingResult -> ShowPending()
    }
}

@Composable
fun ShowPending() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun ShowError(exception: Exception, onTryAgain: ()->Unit) {
    Column(verticalArrangement = Arrangement.Center,
        modifier = Modifier
        .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.error_try_again_later),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, end = 8.dp)
        )
        Text(
            text = exception.localizedMessage ?: "Not found reason",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, end = 8.dp)
        )
        Button(onClick = {
            onTryAgain.invoke()
        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(stringResource(R.string.try_again))
        }
    }

}

@Composable
fun WeatherList(list: List<CurrentWeatherItem>, onNavigateToDetails: (String) -> Unit) {
    Box {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(16.dp))
        {
            items(list, key = { item -> item.id }) { item ->
                WeatherListCard(
                    weather = item,
                    onWeatherClicked = {onNavigateToDetails.invoke(item.nameCity)}
                )
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun ShowErrorPreview() {
    ShowError(java.lang.Exception("Exception reason")){}
}