package com.pivnoydevelopment.onlineschool.search.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.pivnoydevelopment.onlineschool.R
import com.pivnoydevelopment.onlineschool.common.domain.models.Course
import com.pivnoydevelopment.onlineschool.common.ui.CoursesAdapter
import com.pivnoydevelopment.onlineschool.databinding.FragmentCoursesBinding
import com.pivnoydevelopment.onlineschool.search.ui.model.CoursesFragmentState
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
        setupObservers()
        viewModel.loadCourses()
    }

    private fun setupViews() {
        adapter = CoursesAdapter (
            object : CoursesAdapter.CoursesClickListener {
                override fun onDetailsClick() {
                    showToast(requireContext().getString(R.string.dialog_message))
                }

                override fun onFavoriteToggleClick(course: Course) {
                    TODO("Not yet implemented")
                }
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerView.adapter = adapter
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
        }
    }

    fun showLoading() {
        binding.recyclerView.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
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

        adapter?.courses?.clear()
        adapter?.courses?.addAll(courses)
        adapter?.notifyDataSetChanged()
    }
}