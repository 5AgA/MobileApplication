package ddwu.com.mobileapp.week02.fooddbexam_room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database (entities = [Food::class], version=1)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao

    companion object {
        @Volatile
        private var INSTANCE: FoodDatabase? = null  // 정적 변수

        fun getDatabase(context: Context): FoodDatabase {
            // null 일 때, 한번 실행
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