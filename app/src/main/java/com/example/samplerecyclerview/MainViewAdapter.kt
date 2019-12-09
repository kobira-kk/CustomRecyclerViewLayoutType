package com.example.samplerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainViewAdapter(
    private val list: List<ItemAModel>,
    private val listener: ListListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ListListener {
        fun onClickItem(tappedView: View, itemAModel: ItemAModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        println(" ** onCreateViewHolder")
        when (viewType) {
            1 -> return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_a_model, parent, false))
            else -> return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_b_model, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        println(" ** onBindViewHolder")
        if (list[position].type == ItemType.Hoge.ItemA) {
            holder.itemView.findViewById<TextView>(R.id.tv_item_a_model).text = list[position].text
        } else {
            holder.itemView.findViewById<TextView>(R.id.tv_item_b_model_1).text = list[position].text
            holder.itemView.findViewById<TextView>(R.id.tv_item_b_model_2).text = list[position].text
        }

        holder.itemView.setOnClickListener {
            listener.onClickItem(it, list[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(list[position].type == ItemType.Hoge.ItemA) 1 else 2
    }

    override fun getItemCount(): Int = list.size
}