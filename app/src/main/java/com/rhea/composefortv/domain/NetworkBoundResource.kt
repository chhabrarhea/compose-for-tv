package com.rhea.composefortv.domain

import com.rhea.composefortv.di.IODispatcher
import com.rhea.composefortv.domain.model.Resource
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class NetworkBoundResource @Inject constructor(
@IODispatcher val dispatcher: CoroutineContext
) {
  suspend inline fun <T> invoke(
    crossinline query: suspend () -> Response<T>
  ): Resource<T> {
    return withContext(dispatcher) {
       try {
        val result = query.invoke()
        result.body()?.let {
          Resource.Success(it)
        } ?: run {
          Resource.Error(Exception(result.message()))
        }
      } catch (e: Exception) {
        Resource.Error(e)
      }
    }
  }
}