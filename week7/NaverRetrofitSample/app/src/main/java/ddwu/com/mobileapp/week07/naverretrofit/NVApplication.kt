package ddwu.com.mobileapp.week07.naverretrofit

import android.app.Application
import ddwu.com.mobileapp.week07.naverretrofit.data.NVRepository
import ddwu.com.mobileapp.week07.naverretrofit.data.network.NVService

class NVApplication : Application() {
    val nvService by lazy {
        NVService(this)
    }

    val nvRepository by lazy {
        NVRepository(nvService)
    }
}