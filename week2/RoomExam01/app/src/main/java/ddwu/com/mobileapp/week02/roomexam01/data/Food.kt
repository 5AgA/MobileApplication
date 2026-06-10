package ddwu.com.mobileapp.week02.roomexam01.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName="food_table")
data class Food(
    @PrimaryKey (autoGenerate = true)
    val _id: Int,
    var food: String?,
    var country: String?,
) {
    override fun toString(): String {
        return "$_id - $food ($country)"
    }
}