package com.epicteam1.skimountains.feature_ski_places.presentation.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.databinding.FragmentSaveBinding
import com.epicteam1.skimountains.feature_ski_places.core.Constants.DETAILS
import com.epicteam1.skimountains.feature_ski_places.core.Constants.SKI_PLACE_DELETED
import com.epicteam1.skimountains.feature_ski_places.core.Constants.UNDO
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.presentation.adapter.SaveAdapter
import com.epicteam1.skimountains.feature_ski_places.presentation.viewModel.SkiPlaceViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_save.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SaveFragment : Fragment(R.layout.fragment_save) {

    private lateinit var binding: FragmentSaveBinding
    private lateinit var saveAdapter: SaveAdapter
    private val skiPlaceViewModel by viewModel<SkiPlaceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaveBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        setObservers()

        saveAdapter.setOnItemClickListener {
                skiPlace -> onSkiPlaceClick(skiPlace)
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
                    skiPlaceViewModel.deleteSkiPlace(place)
                    Snackbar.make(view, SKI_PLACE_DELETED, Snackbar.LENGTH_LONG).apply {
                        animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
                        setBackgroundTint(Color.DKGRAY)
                        setTextColor(Color.WHITE)
                        show()
                    }.setAction(UNDO) {
                        skiPlaceViewModel.saveSkiPlace(place)
                        skiPlaceViewModel.getAllSkiPlacesSaved()
                    }
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(save_recycler_view)
        }
        skiPlaceViewModel.getAllSkiPlacesSaved()
    }

    private fun onSkiPlaceClick(skiPlace: SkiPlace) {
        val bundle = Bundle().apply {
            putSerializable(DETAILS, skiPlace.skiPlaceId)
        }
        findNavController().navigate(R.id.action_saveSkiPlace_to_details, bundle)
    }

    private fun setObservers() {
        skiPlaceViewModel.skiSavedPlacesListLoaded.observe(
            viewLifecycleOwner,
            ::updateSkiPlacesSavedList
        )
    }

    private fun updateSkiPlacesSavedList(skiPlaces: List<SkiPlace>) {
        saveAdapter.differ.submitList(skiPlaces)
    }

    private fun setAdapter() {
        saveAdapter = SaveAdapter()
        binding.saveRecyclerView.adapter = saveAdapter
        binding.saveRecyclerView.layoutManager = LinearLayoutManager(context)
    }
}