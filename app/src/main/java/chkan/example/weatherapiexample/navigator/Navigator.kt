package chkan.example.weatherapiexample.navigator

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun showForecastScreen(options: NavOptions)

    fun goBack()

}