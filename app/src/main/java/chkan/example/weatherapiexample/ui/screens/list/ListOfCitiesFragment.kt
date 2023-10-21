package chkan.example.weatherapiexample.ui.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import chkan.example.weatherapiexample.core.extensions.onClick
import chkan.example.weatherapiexample.core.views.BaseFragment
import chkan.example.weatherapiexample.databinding.FragmentListOfCitiesBinding
import chkan.example.weatherapiexample.databinding.PartResultBinding
import chkan.example.weatherapiexample.navigator.NavOptions
import chkan.example.weatherapiexample.navigator.navigator
import chkan.example.weatherapiexample.ui.ShareViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfCitiesFragment : BaseFragment() {

    private lateinit var binding: FragmentListOfCitiesBinding
    private lateinit var partResultBinding: PartResultBinding

    private val shareViewModel: ShareViewModel by activityViewModels()

    private val adapter by lazy {
        WeatherListAdapter(object : WeatherActionListener {
            override fun onWeatherDetails(city: String) {
                navigator().showForecastScreen(NavOptions(city))
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListOfCitiesBinding.inflate(inflater, container, false)
        partResultBinding = PartResultBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecyclerView()
        initClicks()
    }

    private fun initRecyclerView() {
        binding.rvCities.adapter = adapter
    }

    private fun initClicks() {
        partResultBinding.tryAgainButton.onClick {
            shareViewModel.tryAgain()
        }
    }

    private fun initViewModel() {
        shareViewModel.citiesWithWeatherResource.observe(viewLifecycleOwner) { result ->
            renderResult(
                root = binding.root,
                result = result,
                onPending = {
                    partResultBinding.progressBar.isVisible = true
                },
                onError = {
                    partResultBinding.errorContainer.isVisible = true
                },
                onSuccess = { successData ->
                    binding.rvCities.isVisible = true
                    adapter.cities = successData.currentListItem
                }
            )
        }
    }

}