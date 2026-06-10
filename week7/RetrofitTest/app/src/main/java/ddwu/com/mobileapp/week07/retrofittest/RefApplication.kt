package ddwu.com.mobileapp.week07.retrofittest

import android.app.Application
import ddwu.com.mobileapp.week07.retrofittest.data.RefRepository
import ddwu.com.mobileapp.week07.retrofittest.data.database.RefDatabase
import ddwu.com.mobileapp.week07.retrofittest.data.network.RefService

class RefApplication : Application() {
    val refDao by lazy {
        RefDatabase.getDatabase(this).refDao()
    }
    val refService by lazy {
        RefService(this)
    }
    val refRepository by lazy {
        RefRepository(refDao, refService)
    }
}