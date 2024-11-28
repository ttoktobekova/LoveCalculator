package com.example.lovecalculator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lovecalculator.data.local.LoveCalculatorEntity
import com.example.lovecalculator.databinding.ItemLoveHistoryBinding
import com.example.lovecalculator.utils.OnClick

class LoveAdapter(private val onClick: OnClick, private val onLongClick: OnClick) :
    ListAdapter<LoveCalculatorEntity, LoveAdapter.ViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLoveHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClick.onClick(getItem(position))
        }
        holder.itemView.setOnLongClickListener {
            if (position >= 0 && position < currentList.size) {
                onClick.onLong(getItem(position))
            }
            true
        }
    }

    inner class ViewHolder(private val binding: ItemLoveHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loveCalculatorEntity: LoveCalculatorEntity) = with(binding) {
            tvFirstName.text = loveCalculatorEntity.firstName
            tvSecondName.text = loveCalculatorEntity.secondName
            tvPercentage.text = loveCalculatorEntity.percentage
            tvTextResult.text = loveCalculatorEntity.result


        }
    }
}


class DiffUtilCallBack : DiffUtil.ItemCallback<LoveCalculatorEntity>() {
    override fun areItemsTheSame(
        oldItem: LoveCalculatorEntity,
        newItem: LoveCalculatorEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: LoveCalculatorEntity,
        newItem: LoveCalculatorEntity
    ): Boolean {
        return oldItem == newItem
    }

}