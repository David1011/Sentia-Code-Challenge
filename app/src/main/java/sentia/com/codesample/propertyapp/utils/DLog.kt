package sentia.com.codesample.propertyapp.utils

import sentia.com.propertyapp.BuildConfig
import android.util.Log

/**
 *  */
class DLog {

    companion object {

        private val mIsLogcatEnabled = BuildConfig.DEBUG

        fun v(tag: String, msg: String) {
            if (mIsLogcatEnabled) {
                Log.v(tag, getTextWithSource(msg))
            }
        }

        fun i(tag: String, msg: String) {
            if (mIsLogcatEnabled) {
                Log.i(tag, getTextWithSource(msg))
            }
        }

        fun d(tag: String, msg: String) {
            if (mIsLogcatEnabled) {
                Log.d(tag, getTextWithSource(msg))
            }
        }

        fun w(tag: String, msg: String) {
            if (mIsLogcatEnabled) {
                Log.w(tag, getTextWithSource(msg))
            }
        }

        fun e(tag: String, msg: String) {
            if (mIsLogcatEnabled) {
                Log.e(tag, getTextWithSource(msg))
            }
        }

        fun wtf(tag: String, msg: String) {
            if (mIsLogcatEnabled) {
                Log.wtf(tag, msg)
            }
        }

        private fun getTextWithSource(text: String): String {
            return "<" + getSourceCodeFileName() + ":" + getLineNumber() + "> " + text
        }

        private fun getSourceCodeFileName(): String {
            return Thread.currentThread().stackTrace[5].fileName
        }

        private fun getLineNumber(): Int {
            return Thread.currentThread().stackTrace[5].lineNumber
        }
    }
}
