package com.epicteam1.skimountains.feature_ski_places.presentation.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epicteam1.skimountains.MainActivity
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.feature_ski_places.domain.util.Constants.DETAILS
import com.epicteam1.skimountains.feature_ski_places.domain.util.Constants.SKI_PLACE_DELETED
import com.epicteam1.skimountains.feature_ski_places.domain.util.Constants.UNDO
import com.epicteam1.skimountains.feature_ski_places.presentation.adapter.SaveAdapter
import com.epicteam1.skimountains.feature_ski_places.presentation.viewModel.SkiViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_save.*

class SaveFragment : Fragment(R.layout.fragment_save) {

    lateinit var viewModel: SkiViewModel
    lateinit var saveAdapter: SaveAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        viewModel.getAllSkiPlacesSaved().observe(viewLifecycleOwner, Observer { it ->
            saveAdapter.differ.submitList(it)
        })
        saveAdapter.setOnItemClickListener { Place ->
            val bundle = Bundle().apply {
                putSerializable(DETAILS, Place)
            }
            findNavController().navigate(R.id.action_saveSkiPlace_to_details, bundle)
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val place = saveAdapter.differ.currentList[position]
                if (direction == ItemTouchHelper.LEFT || direction == ItemTouchHelper.RIGHT) {
                    viewModel.deleteSkiPlace(place)
                    Snackbar.make(view, SKI_PLACE_DELETED, Snackbar.LENGTH_LONG).apply {
                        animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
                        setBackgroundTint(Color.DKGRAY)
                        setTextColor(Color.WHITE)
                        show()
                    }.setAction(UNDO) {
                        viewModel.saveSkiPlace(place)
                    }
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(save_recycler_view)
        }
    }

    private fun setupRecyclerView() {
        saveAdapter = SaveAdapter()
        save_recycler_view.apply {
            adapter = saveAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}