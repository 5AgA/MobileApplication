package ddwu.com.mobileapp.week05.networkbasic

import android.app.Application
import ddwu.com.mobileapp.week05.networkbasic.data.NetworkRepository
import ddwu.com.mobileapp.week05.networkbasic.data.network.NetworkService

class NetworkApplication: Application() {
    val neworkService by lazy {
        NetworkService(this)
    }
    val netRepository by lazy {
        NetworkRepository(neworkService)
    }
}