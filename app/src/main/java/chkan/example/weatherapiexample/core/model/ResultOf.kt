package chkan.example.weatherapiexample.core.model

/**
 * Base class which represents result of some async operation
 */
sealed class ResultOf<T>


/**
 * Operation is in progress
 */
class PendingResult<T> : ResultOf<T>()

/**
 * Operation has finished successfully
 */
class SuccessResult<T>(
    val data: T
) : ResultOf<T>()

/**
 * Operation has finished with error
 */
class ErrorResult<T>(
    val exception: Exception
) : ResultOf<T>()