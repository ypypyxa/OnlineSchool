package com.pivnoydevelopment.onlineschool.favorites.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pivnoydevelopment.common.domain.models.Course
import com.pivnoydevelopment.onlineschool.R
import com.pivnoydevelopment.onlineschool.common.ui.CoursesAdapter
import com.pivnoydevelopment.onlineschool.databinding.FragmentFavoritesBinding
import com.pivnoydevelopment.onlineschool.favorites.ui.model.FavoritesFragmentState
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModel<FavoritesViewModel>()
    private var adapter: CoursesAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()
    }

    private fun setupAdapter() {
        adapter = CoursesAdapter(
            object : CoursesAdapter.CoursesClickListener {
                override fun onDetailsClick() {
                    showDialog()
                    showToast(getString(R.string.dialog_message))
                }

                override fun onFavoriteToggleClick(course: Course) {
                    viewModel.removeFromFavorites(course)
                }
            }
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {

        viewModel.observeState().observe(viewLifecycleOwner) { state ->
            when (state) {
                is FavoritesFragmentState.Empty -> showEmpty(state.message)
                is FavoritesFragmentState.Content -> showContent(state.courses)
                else -> Unit
            }
        }

    }

    private fun showEmpty(message: String) {
        showToast(message)
        binding.recyclerView.visibility = View.GONE
    }

    private fun showContent(courses: List<Course>) {
        binding.recyclerView.visibility = View.VISIBLE
        adapter?.updateList(courses)
    }

    private fun showDialog() {
        MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogStyle)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message))
            .setPositiveButton(getString(R.string.dialog_dismiss)) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}