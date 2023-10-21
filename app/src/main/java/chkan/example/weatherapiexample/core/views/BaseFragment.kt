package chkan.example.weatherapiexample.core.views

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import chkan.example.weatherapiexample.core.model.ErrorResult
import chkan.example.weatherapiexample.core.model.PendingResult
import chkan.example.weatherapiexample.core.model.ResultOf
import chkan.example.weatherapiexample.core.model.SuccessResult

/**
 * Base class for all fragments
 */
abstract class BaseFragment : Fragment() {

    /**
     * Hide all views in the [root] and then call one of the provided lambda functions
     * depending on [result]:
     * - [onPending] is called when [result] is [PendingResult]
     * - [onSuccess] is called when [result] is [SuccessResult]
     * - [onError] is called when [result] is [ErrorResult]
     */
    fun <T> renderResult(root: ViewGroup, result: ResultOf<T>,
                         onPending: () -> Unit,
                         onError: (Exception) -> Unit,
                         onSuccess: (T) -> Unit) {

        root.children.forEach { it.visibility = View.GONE }
        when (result) {
            is SuccessResult -> onSuccess(result.data)
            is ErrorResult -> onError(result.exception)
            is PendingResult -> onPending()
        }

    }
}