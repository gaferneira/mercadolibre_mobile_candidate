package com.mercadolibre.mobile.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mercadolibre.domain.base.Failure
import com.mercadolibre.domain.entities.Product
import com.mercadolibre.mobile.R
import com.mercadolibre.mobile.databinding.FragmentHomeBinding
import com.mercadolibre.mobile.ui.search.SearchFragment
import com.mercadolibre.mobile.utils.Resource
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
    }

    private fun setupView() {
        adapter = ProductsAdapter(this)
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HomeFragment.adapter
        }
        binding.editTextQuery.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionToSearchFragment(binding.editTextQuery.text.toString()))
        }
        binding.editTextQuery.setText(viewModel.searchText.value)
    }

    private fun initObservers() {
        viewModel.products.observe(viewLifecycleOwner, { state ->
            when (state) {
                is Resource.Error -> {
                    showLoading(false)
                    showError(state.failure)
                }
                Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    adapter.setItems(state.data)
                }
            }
        })

        setFragmentResultListener(SearchFragment.REQUEST_KEY) { _: String, bundle: Bundle ->
            val query = bundle.getString(SearchFragment.PARAM_QUERY).orEmpty()
            viewModel.search(query)
            binding.editTextQuery.setText(query)
        }

    }

    override fun onClickProduct(product: Product) {
        findNavController().navigate(HomeFragmentDirections.actionDetailsFragment(product))
    }

    private fun showLoading(show: Boolean) {
        binding.loadingProgressBar.isVisible = show
    }

    private fun showError(failure: Failure) {

        val message = when (failure) {
            is Failure.NetworkConnection -> R.string.error_connection
            else -> R.string.error_server
        }
        Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.action_retry) {
                viewModel.search(binding.editTextQuery.text.toString())
            }
            .show()
    }
}
