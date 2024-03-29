package sentia.com.codesample.propertyapp.network

import sentia.com.codesample.propertyapp.utils.AppConf
import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 *  */
class RequestQueueHelper(context: Context) {

    private val LOG_TAG = AppConf.LOG_TAG_NETWORK

    private var mContext = context
    private var mRequestQueue: RequestQueue = getRequestQueue()

    companion object {
        var mInstance: RequestQueueHelper? = null

        @Synchronized
        fun getInstance(context: Context): RequestQueueHelper {
            if (mInstance == null) {
                mInstance = RequestQueueHelper(context)
            }

            return mInstance!!
        }
    }

    fun getRequestQueue(): RequestQueue {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.applicationContext)
        }

        return mRequestQueue!!
    }

    fun <T> addToRequestQueue(request: Request<T>) {
        getRequestQueue().add(request)
    }

    fun cancelAllRequests() {
        getRequestQueue().cancelAll(mContext.applicationContext)
    }
}
