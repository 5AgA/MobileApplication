package ddum.com.mobile.week09.naverretrofitsample

import android.app.Application
import ddum.com.mobile.week09.naverretrofitsample.data.NVRepository
import ddum.com.mobile.week09.naverretrofitsample.data.network.NVService

class NVApplication : Application() {
    val nvService by lazy {
        NVService(this)
    }

    val nvRepository by lazy {
        NVRepository(nvService)
    }
}