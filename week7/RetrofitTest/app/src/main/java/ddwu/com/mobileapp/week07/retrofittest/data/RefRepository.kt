package ddwu.com.mobileapp.week07.retrofittest.data

import ddwu.com.mobileapp.week07.retrofittest.data.database.RefDao
import ddwu.com.mobileapp.week07.retrofittest.data.database.RefEntity
import ddwu.com.mobileapp.week07.retrofittest.data.network.Movie
import ddwu.com.mobileapp.week07.retrofittest.data.network.RefService
import kotlinx.coroutines.flow.Flow     // Flow 사용 시 직접 추가

class RefRepository(private val refDao: RefDao, private val refService: RefService) {
    val allRefs : Flow<List<RefEntity>> = refDao.getAllRefs()

    suspend fun getNameById(id: Int) : String {
        return refDao.getNameById(id)
    }

    suspend fun getMovies(key: String, date: String) : List<Movie>? {
        return refService.getMovies(key, date)
    }
}

