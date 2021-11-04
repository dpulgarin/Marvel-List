package com.dpulgarin.marvellist.core

import com.dpulgarin.marvellist.application.AppConstants
import kotlinx.coroutines.coroutineScope
import java.net.InetSocketAddress
import java.net.Socket

object InternetCheck {
    suspend fun isNetworkAvailable() = coroutineScope {
        return@coroutineScope try {
            val sock = Socket()
            val socketAddress = InetSocketAddress(AppConstants.HOSTNAME, AppConstants.PORT)
            sock.connect(socketAddress, AppConstants.TIMEOUT)
            sock.close()
            true
        } catch (e: Exception) {
            false
        }
    }
}