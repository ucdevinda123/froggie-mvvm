package com.froggie.design.ui.home

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.froggie.design.R
import com.froggie.design.databinding.FragmentHomeBinding
import com.froggie.design.repository.data.Result
import com.froggie.design.repository.util.Category
import com.froggie.design.repository.util.Resource
import com.froggie.design.ui.home.adapter.DesignListAdapter
import com.froggie.design.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), DesignListAdapter.OnItemClickListener {

    private lateinit var homeBinding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var designAdapter: DesignListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeBinding = FragmentHomeBinding.bind(view)
        homeBinding.shimmerLayout.isVisible = true
        homeBinding.shimmerLayout.startShimmer()
        initDesignList()
        handleCategorySelection()
        registerBackPress()
    }

    private fun registerBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            showExitDialog()
        }
    }

    private fun initDesignList() {
        designAdapter = DesignListAdapter()
        designAdapter.setItemClickListener(this)
        homeBinding.apply {
            rvHomeDesign.adapter = designAdapter
            rvHomeDesign.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        }
    }

    override fun onItemClick(designData: Result) {
        val navigationAction = HomeFragmentDirections.actionHomeFragmentToPlayerFragment(designData)
        findNavController().navigate(navigationAction)
    }

    private fun handleCategorySelection() {
        renderCategorySelectionStatus()
        homeBinding.itemCategory.designCategories.setOnSelectListener {
            homeBinding.shimmerLayout.startShimmer()
            when (it) {
                homeBinding.itemCategory.popular -> {
                    getPopularDesigns()
                    return@setOnSelectListener
                }

                homeBinding.itemCategory.featured -> {
                    getFeaturedDesigns()
                    return@setOnSelectListener
                }

                homeBinding.itemCategory.recent -> {
                    getRecentDesigns()
                    return@setOnSelectListener
                }
            }
        }
    }

    private fun renderCategorySelectionStatus() {
        lifecycleScope.launch {
            homeViewModel.getCategoryStatus().collect { designCategory ->
                designCategory?.let {
                    when (designCategory.id) {
                        Category.POPULAR.value -> {
                            homeBinding.itemCategory.designCategories.selectButton(homeBinding.itemCategory.popular)
                            return@collect
                        }

                        Category.FEATURED.value -> {
                            homeBinding.itemCategory.designCategories.selectButton(homeBinding.itemCategory.featured)
                            return@collect
                        }

                        Category.RECENT.value -> {
                            homeBinding.itemCategory.designCategories.selectButton(homeBinding.itemCategory.recent)
                            return@collect
                        }
                        else -> {
                            homeBinding.itemCategory.designCategories.selectButton(homeBinding.itemCategory.popular)
                            return@collect
                        }

                    }

                }
            }
        }
    }


    private fun getPopularDesigns() {
        lifecycleScope.launch {
            homeViewModel.pickCategory(Category.POPULAR.value, true)
            homeViewModel.getPopularAnimations().collect { designList ->
                run {
                    renderDesignData(designList)
                }
            }
        }
    }

    private fun getFeaturedDesigns() {
        lifecycleScope.launch {
            homeViewModel.pickCategory(Category.FEATURED.value, true)
            homeViewModel.getFeaturedAnimations().collect { designList ->
                run {
                    renderDesignData(designList)
                }
            }
        }
    }

    private fun getRecentDesigns() {
        lifecycleScope.launch {
            homeViewModel.pickCategory(Category.RECENT.value, true)
            homeViewModel.getRecentAnimations().collect { designList ->
                run {
                    renderDesignData(designList)
                }
            }
        }
    }

    private fun renderDesignData(designList: Resource<List<Result>>) {
        designAdapter.submitList(designList.data)
        val isLoading =
            designList is Resource.Loading && designList.data.isNullOrEmpty()
        homeBinding.shimmerLayout.isVisible = isLoading
        if (!isLoading) {
            homeBinding.shimmerLayout.stopShimmer()
        }
    }

    private fun showExitDialog() {
        val alert = context?.let {
            AlertDialog.Builder(it)
                .setMessage(getString(R.string.are_you_sure_want_to_exit))
                .setCancelable(false)
                .setPositiveButton(
                    getString(R.string.yes)
                ) { _, _ ->
                    activity?.finishAffinity();
                    activity?.finish()
                }
                .setNegativeButton(
                    getString(R.string.no)
                ) { dialog, _ -> dialog.cancel() }

        }?.create()
        alert?.show()
    }
}