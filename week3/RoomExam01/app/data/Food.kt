package ddwu.com.mobile.roomexam01.data


data class Food(
    val _id: Int,
    var food: String?,
    var country: String?)
{
    override fun toString(): String {
        return "$_id - $food ($country)"
    }
}
