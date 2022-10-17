package com.epicteam1.skimountains.feature_ski_places.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.epicteam1.skimountains.MainActivity
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.feature_ski_places.domain.model.Category
import com.epicteam1.skimountains.feature_ski_places.presentation.adapter.CategoryAdapter
import com.epicteam1.skimountains.feature_ski_places.presentation.adapter.SkiPlacesAdapter
import com.epicteam1.skimountains.feature_ski_places.presentation.viewModel.SkiViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var viewModel: SkiViewModel
    lateinit var skiPlacesAdapter: SkiPlacesAdapter  // TODO
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var ctg: Category

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        setupRecyclerViewMain()
        setupRecyclerViewCategory()
    }


    private fun setupRecyclerViewMain() {
        skiPlacesAdapter = SkiPlacesAdapter()
        rv_ski_places.apply {
            adapter = skiPlacesAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupRecyclerViewCategory() {
        categoryAdapter = CategoryAdapter()
        recycler_view_categories.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}