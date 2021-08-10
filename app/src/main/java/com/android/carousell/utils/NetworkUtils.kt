package com.android.carousell.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.android.carousell.MyApplication

object NetworkUtils {
    fun isNetworkAvailable(): Boolean{
        val connectivityManager =  MyApplication.applicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        }?:false
    }
}