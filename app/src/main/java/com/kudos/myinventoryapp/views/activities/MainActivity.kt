package com.kudos.myinventoryapp.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kudos.myinventoryapp.databinding.ActivityMainBinding
import com.kudos.myinventoryapp.viewmodels.InventoryViewModel
import com.kudos.myinventoryapp.views.adapters.ItemsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var itemsAdapter: ItemsAdapter
    private lateinit var binding: ActivityMainBinding

    private val inventoryViewModel: InventoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            addNewItemFloatingActionButton.setOnClickListener {
                startActivity(Intent(this@MainActivity, AddNewItemActivity::class.java))
            }
        }
        setupItemsList()
        observeItemsList()
    }

    private fun observeItemsList() {
        lifecycleScope.launch {

            inventoryViewModel.allItems.observe(this@MainActivity) {
                itemsAdapter.submitList(it)
            }

        }
    }

    private fun setupItemsList() {
        itemsAdapter = ItemsAdapter {
            startActivity(Intent(this, ItemDetailsActivity::class.java).putExtra("ITEM", it))
        }
        binding.itemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemsAdapter
        }
    }
}