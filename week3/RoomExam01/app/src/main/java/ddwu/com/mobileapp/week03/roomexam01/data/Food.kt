package ddwu.com.mobile.roomexam01.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class Food(
    @PrimaryKey (autoGenerate = true)
    val _id: Int,

    @ColumnInfo(name="food")
    var food: String?,

    @ColumnInfo(name = "country")
    var country: String?
) {
    // override toString()
    override fun toString(): String {
        return "$_id - $food ($country)"
    }
}
