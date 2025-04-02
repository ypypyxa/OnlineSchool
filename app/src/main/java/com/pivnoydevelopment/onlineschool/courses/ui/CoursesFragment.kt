package com.pivnoydevelopment.onlineschool.courses.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pivnoydevelopment.onlineschool.R
import com.pivnoydevelopment.onlineschool.common.domain.models.Course
import com.pivnoydevelopment.onlineschool.common.ui.CoursesAdapter
import com.pivnoydevelopment.onlineschool.databinding.FragmentCoursesBinding
import com.pivnoydevelopment.onlineschool.courses.ui.model.CoursesFragmentState
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoursesFragment : Fragment() {

    private lateinit var binding: FragmentCoursesBinding

    private val viewModel: CoursesViewModel by viewModel<CoursesViewModel>()

    private var adapter: CoursesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupListeners()
        setupObservers()
    }

    private fun setupViews() {
        adapter = CoursesAdapter (
            object : CoursesAdapter.CoursesClickListener {
                override fun onDetailsClick() {
                    showDialog()
                    showToast(requireContext().getString(R.string.dialog_message))
                }

                override fun onFavoriteToggleClick(course: Course) {
                    TODO("Not yet implemented")
                }
            }
        )

        binding.recyclerView.adapter = adapter
    }

    private fun setupListeners() {
        binding.sortButton.setOnClickListener {
            viewModel.sort()
        }
    }

    private fun setupObservers() {

        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }

        viewModel.observeShowToast().observe(viewLifecycleOwner) {
            showToast(it)
        }
    }

    private fun showToast(additionalMessage: String) {
        Toast.makeText(requireContext(), additionalMessage, Toast.LENGTH_LONG)
            .show()
    }

    private fun render(state: CoursesFragmentState) {
        when (state) {
            is CoursesFragmentState.Content -> showContent(state.courses)
            is CoursesFragmentState.Empty -> showEmpty(state.message)
            is CoursesFragmentState.Error -> showError(state.errorMessage)
            is CoursesFragmentState.Loading -> showLoading()
            is CoursesFragmentState.Sort -> sort(state.courses)
        }
    }

    fun showLoading() {
        binding.recyclerView.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        binding.sortButton.isEnabled = false
    }

    fun showError(errorMessage: String) {
        binding.recyclerView.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        showToast(errorMessage)
    }

    fun showEmpty(emptyMessage: String) {
        showError(emptyMessage)
    }

    fun showContent(courses: List<Course>) {
        binding.recyclerView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        binding.sortButton.isEnabled = true

        adapter?.courses?.clear()
        adapter?.courses?.addAll(courses)
        adapter?.notifyDataSetChanged()
    }

    fun sort(courses: List<Course>) {
        adapter?.courses?.clear()
        adapter?.courses?.addAll(courses)
        adapter?.notifyDataSetChanged()
    }

    private fun showDialog() {
        MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogStyle)
            .setTitle(requireContext().getString(R.string.dialog_title))
            .setMessage(requireContext().getString(R.string.dialog_message))
            .setPositiveButton(requireContext().getString(R.string.dialog_dismiss)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}