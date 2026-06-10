package ddwu.com.mobileapp.week12.maptest

import android.app.Application
import ddwu.com.mobileapp.week12.maptest.data.MapRepository
import ddwu.com.mobileapp.week12.maptest.data.database.RefDatabase
import ddwu.com.mobileapp.week12.maptest.data.network.RefService

class MapApplication : Application() {
    val refDao by lazy {
        RefDatabase.getDatabase(this).refDao()
    }
    val refService by lazy {
        RefService()
    }
    val mapRepository by lazy {
        MapRepository(refDao, refService)
    }
}