package sentia.com.codesample.propertyapp.network

import sentia.com.codesample.propertyapp.models.Properties
import sentia.com.codesample.propertyapp.utils.AppConf
import sentia.com.codesample.propertyapp.utils.DLog
import sentia.com.codesample.propertyapp.utils.NetworkConstants
import android.content.Context
import android.net.Uri
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import java.net.URL

/**
 *  */
class RequestsProcessor(context: Context, requestResponseListener: RequestResponseListener) {

    companion object {
        private const val LOG_TAG = AppConf.LOG_TAG_NETWORK
    }

    private var mContext = context
    private var mRequestResponseListener = requestResponseListener

    fun getProperties() {
        try {
            val builder = Uri.Builder()
                    .scheme(NetworkConstants.SCHEME_HTTPS)
                    .authority(NetworkConstants.URL_AUTHORITY)
                    .appendPath(NetworkConstants.URL_PATH_PROPERTIES)

            val url = URL(builder.build().toString()).toString()

            val objectRequest = JsonObjectRequest(
                    Request.Method.GET, url, null,
                    Response.Listener { response ->
                        val gson = Gson()
                        val properties: Properties = gson.fromJson(response.toString(), Properties::class.java)
                        mRequestResponseListener.responseOk(properties)
                    },
                    Response.ErrorListener { error ->
                        mRequestResponseListener.responseError(error)
                    }
            )

            objectRequest.tag = mContext.applicationContext
            RequestQueueHelper.getInstance(mContext).addToRequestQueue(objectRequest)
        } catch (e: Exception) {
            DLog.e(LOG_TAG, "Exception in makeRequest: " + e.toString())
        }

    }

    interface RequestResponseListener {
        fun responseOk(properties: Properties)

        fun responseError(error: VolleyError)
    }
}
