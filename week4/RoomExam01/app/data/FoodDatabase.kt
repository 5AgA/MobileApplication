package ddwu.com.mobile.roomexam01.data

import android.content.Context

abstract class FoodDatabase {

    abstract fun foodDao(): FoodDao

    companion object {
        @Volatile
        private var INSTANCE: FoodDatabase? = null

//        fun getDatabase(context: Context): FoodDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder
//            }
//
//        }
    }

}