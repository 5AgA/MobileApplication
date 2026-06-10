package ddwu.com.mobileapp.week06.moviexmlparsing

import android.app.Application
import ddwu.com.mobileapp.week06.moviexmlparsing.data.MXPRepository
import ddwu.com.mobileapp.week06.naverxmlparsing.data.network.NetworkService

class MXPApplication : Application(){
    val networkService by lazy {
        NetworkService(this)
    }
    val networkRepo by lazy {
        MXPRepository(networkService)
    }
}