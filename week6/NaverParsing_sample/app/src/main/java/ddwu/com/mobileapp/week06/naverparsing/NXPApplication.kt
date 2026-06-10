package ddwu.com.mobileapp.week06.naverparsing

import android.app.Application
import ddwu.com.mobileapp.week06.naverparsing.data.NXPRepository
import ddwu.com.mobileapp.week06.naverparsing.data.network.NetworkService

class NXPApplication : Application(){
    val networkService by lazy {
        NetworkService(this)
    }
    val networkRepo by lazy {
        NXPRepository(networkService)
    }
}