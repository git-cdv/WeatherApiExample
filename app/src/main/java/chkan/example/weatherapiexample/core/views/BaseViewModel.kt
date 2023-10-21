package chkan.example.weatherapiexample.core.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chkan.example.weatherapiexample.core.model.ErrorResult
import chkan.example.weatherapiexample.core.model.ResultOf
import chkan.example.weatherapiexample.core.model.SuccessResult
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException


typealias LiveResult<T> = LiveData<ResultOf<T>>
typealias MutableLiveResult<T> = MutableLiveData<ResultOf<T>>

/**
 * Base class for all view-models.
 */
open class BaseViewModel : ViewModel() {

    /**
     * Launch the specified suspending [block] and use its result as a valid for the
     * provided [liveResult].
     */
    fun <T> into(liveResult: MutableLiveResult<T>, block: suspend () -> T) {
        viewModelScope.launch {
            try {
                liveResult.postValue(SuccessResult(block()))
            } catch (e: Exception) {
                if (e !is CancellationException) liveResult.postValue(ErrorResult(e))
            }
        }
    }

}