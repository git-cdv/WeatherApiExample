package chkan.example.weatherapiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import chkan.example.weatherapiexample.navigator.NavOptions
import chkan.example.weatherapiexample.navigator.Navigator
import chkan.example.weatherapiexample.ui.screens.details.ForecastByCityFragment
import chkan.example.weatherapiexample.ui.screens.list.ListOfCitiesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, ListOfCitiesFragment())
                .commit()
        }
    }

    override fun showForecastScreen(options: NavOptions) {
        launchFragment(ForecastByCityFragment.newInstance(options))
    }

    override fun goBack() {
        onBackPressedDispatcher.onBackPressed()
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            .setReorderingAllowed(true)
            .addToBackStack(fragment.tag)
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

}