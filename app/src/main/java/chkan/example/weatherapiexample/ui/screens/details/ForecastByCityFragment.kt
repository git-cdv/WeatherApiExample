package chkan.example.weatherapiexample.ui.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import chkan.example.weatherapiexample.core.extensions.onClick
import chkan.example.weatherapiexample.core.extensions.parcelable
import chkan.example.weatherapiexample.databinding.FragmentDetailsBinding
import chkan.example.weatherapiexample.navigator.NavOptions
import chkan.example.weatherapiexample.navigator.navigator
import chkan.example.weatherapiexample.ui.ShareViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastByCityFragment : Fragment() {

    private lateinit var navOptions: NavOptions

    private val shareViewModel: ShareViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navOptions = arguments?.parcelable(ARG_OPTIONS) ?:
                throw IllegalArgumentException("Can't launch CityForecastFragment without options")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.tvCityName.text = navOptions.city

        binding.rvForecast.adapter = ForecastListAdapter().apply {
            shareViewModel.getForecastDataByCity(navOptions.city)?.let {
                forecasts = it
            }
        }

        binding.ivArrowBack.onClick {
            navigator().goBack()
        }
        return binding.root
    }

    companion object {
        const val ARG_OPTIONS = "EXTRA_OPTIONS"

        fun newInstance(options: NavOptions): ForecastByCityFragment {
            val args = Bundle()
            args.putParcelable(ARG_OPTIONS, options)
            val fragment = ForecastByCityFragment()
            fragment.arguments = args
            return fragment
        }
    }

}