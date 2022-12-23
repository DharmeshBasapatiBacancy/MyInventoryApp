package com.kudos.myinventoryapp.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kudos.myinventoryapp.databinding.RowItemItemsBinding
import com.kudos.myinventoryapp.db.entity.Item
import com.kudos.myinventoryapp.utils.CommonUtils.getFormattedPrice

class ItemsAdapter(private val onItemClick: (Item) -> Unit) :
    ListAdapter<Item, ItemsAdapter.ViewHolder>(callback) {

    companion object {
        val callback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(
                oldItem: Item,
                newItem: Item
            ) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Item,
                newItem: Item
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(val rowItemItemsBinding: RowItemItemsBinding) :
        RecyclerView.ViewHolder(rowItemItemsBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        RowItemItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            rowItemItemsBinding.apply {
                val item = getItem(position)
                if (position == 0) {
                    itemLabelTextView.visibility = View.VISIBLE
                    priceLabelTextView.visibility = View.VISIBLE
                    quantityLabelTextView.visibility = View.VISIBLE
                    viewDivider.visibility = View.VISIBLE
                }else{
                    itemLabelTextView.visibility = View.GONE
                    priceLabelTextView.visibility = View.GONE
                    quantityLabelTextView.visibility = View.GONE
                    viewDivider.visibility = View.GONE
                }
                itemTextView.text = item.itemName
                priceTextView.text = item.getFormattedPrice()
                quantityTextView.text = item.quantityInStock.toString()

                itemView.setOnClickListener {
                    onItemClick(item)
                }

            }
        }
    }

}