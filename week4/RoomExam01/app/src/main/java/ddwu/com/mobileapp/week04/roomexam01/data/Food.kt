package ddwu.com.mobileapp.week04.roomexam01.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "food_table")
data class Food(
    @PrimaryKey (autoGenerate = true)
    val _id: Int,

    @ColumnInfo (name="food")
    var foodName: String?,

    var country: String?
) {
    // override toString()
    override fun toString(): String {
        return "$_id - $foodName ($country)"
    }
}
