package com.patch.bookinfoapp.common.manager

import android.widget.Toast
import com.google.gson.Gson
import com.patch.bookinfoapp.BookInfoApplication
import com.patch.bookinfoapp.common.manager.RequestManager.parseErrorBody
import com.patch.bookinfoapp.data.entity.ErrorData
import okhttp3.ResponseBody
import retrofit2.Response

object RequestManager {
    fun parseErrorBody(errorBody: ResponseBody?): ErrorData {
        val error = getDefaultErrorBody()
        errorBody?.let {
            try {
                val adapter = Gson().getAdapter(ErrorData::class.java)
                return adapter.fromJson(it.string())
            } catch (e: Exception) { e.printStackTrace() }
        }
        return error
    }

    fun getDefaultErrorBody() = ErrorData(
        errorType = "Unknown",
        message = "Unknown Error 발생"
    )
}

fun <T> Response<T>?.request(
    listener: OnResponseListener<T>? = null
) {
    listener?.let {
        this?.let { response ->
            if (response.isSuccessful) {
                listener.onSuccess(response.body())
            } else {
                val errorData = parseErrorBody(response.errorBody())
                Toast.makeText(BookInfoApplication.applicationContext(), errorData.message, Toast.LENGTH_SHORT).show()
                listener.onError(parseErrorBody(response.errorBody()))
            }
        } ?: listener.onError(RequestManager.getDefaultErrorBody())
    }
}

interface OnResponseListener<T> {
    fun onSuccess(response: T?)
    fun onError(error: ErrorData) {}
}