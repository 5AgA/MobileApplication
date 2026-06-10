package ddwu.com.mobileapp.week03.roomexam01

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobile.roomexam01.data.Food
import ddwu.com.mobileapp.week03.roomexam01.databinding.ListItemBinding

class FoodAdapter(val foods: ArrayList<Food>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    val TAG = "FoodAdapter"

    override fun getItemCount(): Int {
        return foods.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.itemBinding.tvItem.text = foods[position].toString()
        holder.itemBinding.root.setOnLongClickListener{
            itemLongClickListener?.onItemLongClickListener(it, position)
            true
        }
    }

    class FoodViewHolder(val itemBinding: ListItemBinding)
        : RecyclerView.ViewHolder(itemBinding.root)


    interface OnItemLongClickListener {
        fun onItemLongClickListener(view: View, pos: Int)
    }

    var itemLongClickListener : OnItemLongClickListener? = null

    fun setOnItemLongClickListener(listener: OnItemLongClickListener?) {
        itemLongClickListener = listener
    }

}