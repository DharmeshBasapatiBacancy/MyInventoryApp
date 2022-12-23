package com.kudos.myinventoryapp.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kudos.myinventoryapp.databinding.ActivityItemDetailsBinding
import com.kudos.myinventoryapp.db.entity.Item
import com.kudos.myinventoryapp.utils.Status
import com.kudos.myinventoryapp.utils.ViewUtils.toast
import com.kudos.myinventoryapp.viewmodels.InventoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ItemDetailsActivity : AppCompatActivity() {

    private lateinit var ITEM: Item
    private lateinit var binding: ActivityItemDetailsBinding

    private val inventoryViewModel: InventoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ITEM = intent.extras?.getSerializable("ITEM") as Item
        binding.lifecycleOwner = this
        binding.item = ITEM
        binding.apply {

            updateItemFloatingActionButton.setOnClickListener {
                startActivity(Intent(this@ItemDetailsActivity, AddNewItemActivity::class.java))
            }

            sellAction.setOnClickListener {

            }

            deleteAction.setOnClickListener {
                deleteItem()
            }

        }
    }

    private fun deleteItem() {
        lifecycleScope.launch {
            inventoryViewModel.deleteItem(ITEM)

            inventoryViewModel.deleteStatus.observe(this@ItemDetailsActivity) {
                when (it.status) {
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        finish()
                    }
                    Status.ERROR -> {
                        toast(it.message.toString())
                    }
                }
            }
        }
    }
}