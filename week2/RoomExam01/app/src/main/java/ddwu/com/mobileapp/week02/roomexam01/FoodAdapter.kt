package ddwu.com.mobileapp.week02.roomexam01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobileapp.week02.roomexam01.data.Food
import ddwu.com.mobileapp.week02.roomexam01.databinding.FoodItemBinding

class FoodAdapter(val foods: List<Food>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>(){
    override fun getItemCount(): Int {
        return foods.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val foodBinding = FoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(foodBinding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.foodBinding.tvFood.text = foods[position].toString()
    }

    class FoodViewHolder(val foodBinding: FoodItemBinding): RecyclerView.ViewHolder(foodBinding.root)
}