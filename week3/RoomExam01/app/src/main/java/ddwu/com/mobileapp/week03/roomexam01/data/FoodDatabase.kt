package ddwu.com.mobile.roomexam01.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [Food::class], version = 1)
abstract class FoodDatabase : RoomDatabase() {
    // 데이터베이스에서 DAO를 가져옴
    abstract fun foodDao(): FoodDao

    // Singleton Pattern
    companion object {
        // 정적 변수에 저장하여 사용
        @Volatile
        private var INSTANCE: FoodDatabase? = null

        fun getDatabase(context: Context): FoodDatabase {
            // 객체가 존재하지 않을 경우에만 생성 : singleton pattern
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, FoodDatabase::class.java, "food_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}