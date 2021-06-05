package com.mercadolibre.domain.base

import timber.log.Timber
import java.util.concurrent.TimeoutException

sealed class Failure(val error: Exception?) {
    class UnknownException(error: Exception? = null) : Failure(error)
    class NetworkConnection(error: Exception? = null) : Failure(error)
    class ServerError(error: Exception? = null) : Failure(error)
    class FormError(error: Exception? = null) : Failure(error)
    class DataNotFound(error: Exception? = null) : Failure(error)

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure(error: Exception?) : Failure(error)

    companion object {
        fun analyzeException(exception: Exception?): Failure {
            // TODO Create cases
            return when (exception) {
                is TimeoutException -> NetworkConnection(exception)
                else -> {
                    exception?.run {
                        Timber.e(exception)
                    }
                    UnknownException(exception)
                }
            }
        }
    }
}
