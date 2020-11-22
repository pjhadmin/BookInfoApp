package com.patch.bookinfoapp.common.corourine

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.patch.bookinfoapp.BookInfoApplication
import com.patch.bookinfoapp.data.entity.ErrorResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

sealed class State {
    object CoroutinesStarted : State()
    class CoroutinesFinished(val throwable: Throwable?) : State()
}

sealed class StateException {
    class Network(val exception: Exception) : StateException()
    class Error(val code: String?, val error: ErrorResponse?) : StateException()
}

interface StateCoroutine {

    val exception: LiveData<StateException>
    val coroutineState: LiveData<State>

    fun CoroutineScope.stateLaunch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job

}

class StateCoroutineImpl : StateCoroutine {

    private val _exception = MutableLiveData<StateException>()
    override val exception: LiveData<StateException> get() = _exception

    private val _coroutineState = MutableLiveData<State>()
    override val coroutineState: LiveData<State> get() = _coroutineState

    override fun CoroutineScope.stateLaunch(
        context: CoroutineContext,
        start: CoroutineStart,
        block: suspend CoroutineScope.() -> Unit
    ): Job = launch(context, start) {
        _coroutineState.postValue(State.CoroutinesStarted)
        val result = runCatching {
            block()
        }.onFailure {
            _exception.postValue(it.toException())
        }
        _coroutineState.postValue(
            State.CoroutinesFinished(
                result.exceptionOrNull()
            )
        )
    }
}

fun Throwable.toException(): StateException {
    return when (this) {
        is IOException -> {
            Toast.makeText(BookInfoApplication.applicationContext(), "에러가 발생했습니다", Toast.LENGTH_SHORT).show()
            StateException.Network(IOException())
        }
        is HttpException -> {
            val error = convertErrorBody(this)
            Toast.makeText(BookInfoApplication.applicationContext(), error?.message, Toast.LENGTH_SHORT).show()
            StateException.Error(code().toString(), error)
        }
        else -> {
            StateException.Error(null, null)
        }
    }
}

fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    runCatching {
        throwable.response()?.errorBody()?.string().let {
            return Gson().fromJson(it, ErrorResponse::class.java)
        }
    }
    return null
}