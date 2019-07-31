package com.paysky.data.network

import androidx.annotation.NonNull
import com.paysky.data.model.Repository
import com.paysky.data.network.Status
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

data class Resource<T>(val status: Int, val data: T?, val errorType: Int?) {
    fun isError() = status == STATUS_ERROR

    companion object {
        const val STATUS_SUCCESS = 1
        const val STATUS_ERROR = -1
        const val STATUS_ZERO_ITEMS = 2

        /**
         * Helper method to create fresh state resource
         */
        fun <T> success(@NonNull data: T): Resource<T> {
            return Resource(STATUS_SUCCESS, data, null)
        }

        /**
         * Helper method to create error state Resources. Error state might also have the current data, if any
         */
        fun <T> error(item: T? = null): Resource<T> {
            return Resource(STATUS_ERROR, item, null)
        }

        fun <T> zeroItems(item: T? = null): Resource<T> {
            return Resource(STATUS_ZERO_ITEMS, item, null)
        }
    }
}
