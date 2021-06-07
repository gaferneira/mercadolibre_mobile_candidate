package com.mercadolibre.mobile.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mercadolibre.domain.base.Failure
import com.mercadolibre.domain.entities.Product
import com.mercadolibre.mobile.R
import com.mercadolibre.mobile.databinding.FragmentHomeBinding
import com.mercadolibre.mobile.databinding.ItemHomeProductBinding
import com.mercadolibre.mobile.ui.search.SearchFragment
import com.mercadolibre.mobile.utils.extensions.navigate
import com.mercadolibre.mobile.utils.extensions.setExitToFullScreenTransition
import com.mercadolibre.mobile.utils.extensions.setReturnFromFullScreenTransition
import com.mercadolibre.mobile.utils.view.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), ProductsAdapter.ProductsListener {

    private lateinit var adapter: ProductsAdapter
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()

        setExitToFullScreenTransition()
        setReturnFromFullScreenTransition()

    }

    private fun setupView() {
        adapter = ProductsAdapter(this)
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HomeFragment.adapter

            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
        binding.editTextSearch.setOnClickListener {
            goToSearchView()
        }
    }

    private fun initObservers() {
        viewModel.products.observe(viewLifecycleOwner, { state ->
            when (state) {
                Resource.None -> {
                    updateView(showInitialView = true, products = listOf())
                }
                is Resource.Error -> {
                    updateView(error = state.failure)
                }
                Resource.Loading -> {
                    updateView(showLoading = true)
                }
                is Resource.Success -> {
                    updateView(products = state.data)
                }
            }
        })

        viewModel.searchText.observe(viewLifecycleOwner, {
            binding.editTextSearch.setText(it)
        })

        setFragmentResultListener(SearchFragment.REQUEST_KEY) { _: String, bundle: Bundle ->
            val query = bundle.getString(SearchFragment.PARAM_QUERY).orEmpty()
            viewModel.search(query)
        }
    }

    private fun goToSearchView() {
        val extraInfoForSharedElement = FragmentNavigatorExtras(
            binding.editTextSearch to binding.editTextSearch.transitionName
        )
        navigate(HomeFragmentDirections.actionToSearchFragment(
            binding.editTextSearch.text.toString()),
            extraInfoForSharedElement
        )
    }

    override fun onClickProduct(product: Product, view: View) {
        val transitionName = view.transitionName
        val extraInfoForSharedElement = FragmentNavigatorExtras(
            view to transitionName
        )
        navigate(HomeFragmentDirections.actionDetailsFragment(product, transitionName), extraInfoForSharedElement)
    }

    private fun updateView(showInitialView : Boolean = false,
                           showLoading: Boolean = false,
                           error : Failure? = null,
                           products : List<Product>? = null) {

        binding.containerGetStarted.isVisible = showInitialView
        binding.loadingProgressBar.isVisible = showLoading
        error?.let {
            showError(it)
        }

        products?.let {
            adapter.setItems(it)
        }

        binding.containerEmptyView.isVisible = products?.isEmpty() == true && showInitialView.not()
    }

    private fun showError(failure: Failure) {
        val message = when (failure) {
            is Failure.NetworkConnection -> R.string.error_connection
            else -> R.string.error_server
        }
        Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.action_retry) {
                viewModel.search(binding.editTextSearch.text.toString())
            }
            .show()
    }
}
