package com.kudos.myinventoryapp.views.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kudos.myinventoryapp.databinding.ActivityAddNewItemBinding
import com.kudos.myinventoryapp.db.entity.Item
import com.kudos.myinventoryapp.utils.Status
import com.kudos.myinventoryapp.utils.ViewUtils.toast
import com.kudos.myinventoryapp.viewmodels.InventoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddNewItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewItemBinding

    private val inventoryViewModel: InventoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            saveAction.setOnClickListener {
                addNewItem()
            }
        }
    }

    private fun addNewItem() {
        binding.apply {
            if (itemName.text?.isEmpty()!! || itemPrice.text?.isEmpty()!! || itemCount.text?.isEmpty()!!) {
                toast("Please fill all the fields...")
            } else {
                insertItem(
                    itemName.text.toString(),
                    itemPrice.text.toString(),
                    itemCount.text.toString()
                )
            }
        }
    }

    private fun insertItem(name: String, price: String, count: String) {
        lifecycleScope.launch {
            val item =
                Item(itemName = name, itemPrice = price.toDouble(), quantityInStock = count.toInt())
            inventoryViewModel.insertItem(item)

            inventoryViewModel.insertStatus.observe(this@AddNewItemActivity) {
                when (it.status) {
                    Status.SUCCESS -> {
                        toast("Item Added !!!")
                        finish()
                    }
                    Status.ERROR -> {
                        toast(it.message.toString())
                    }
                    Status.LOADING -> {}
                }
            }
        }
    }
}