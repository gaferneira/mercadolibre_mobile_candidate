package com.mercadolibre.mobile.ui.search

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.mercadolibre.domain.entities.RecentSearch
import com.mercadolibre.mobile.R
import com.mercadolibre.mobile.databinding.FragmentSearchBinding
import com.mercadolibre.mobile.utils.view.Resource
import com.mercadolibre.mobile.utils.extensions.autoCleared
import com.mercadolibre.mobile.utils.extensions.setSharedElementTransitionOnEnter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), RecentSearchAdapter.RecentSearchListener {

    private var binding: FragmentSearchBinding by autoCleared()
    private val viewModel: SearchViewModel by viewModels()
    private val args: SearchFragmentArgs by navArgs()

    private lateinit var adapter: RecentSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        setupView()
        return binding.root
    }

    private fun setupView() {
        adapter = RecentSearchAdapter(this)
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@SearchFragment.adapter
        }
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.editTextQuery.setText(args.query)
        binding.editTextQuery.setOnEditorActionListener { editText, actionId, _ ->
            return@setOnEditorActionListener if (actionId == IME_ACTION_SEARCH) {
                doSearch(editText.text.toString())
                true
            } else {
                false
            }

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchTransitionOnEnter()
        showKeyboard()
        initObservers()
    }

    private fun initObservers() {
        viewModel.recentSearchList.observe(viewLifecycleOwner, { state ->
            when (state) {
                is Resource.Success -> {
                    adapter.setItems(state.data)
                }
                else -> {
                    adapter.setItems(listOf())
                }
            }
        })
    }

    private fun showKeyboard() {
        binding.editTextQuery.requestFocus()
        (requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    override fun onClickRecentSearch(item: RecentSearch) {
        doSearch(item.query)
    }

    private fun doSearch(query: String) {
        viewModel.addRecentSearch(query)
        setFragmentResult(REQUEST_KEY, bundleOf(PARAM_QUERY to query))
        findNavController().popBackStack()
    }

    private fun setSearchTransitionOnEnter() {
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.search_transition)
    }

    companion object {
        const val REQUEST_KEY = "SearchFragmentResult"
        const val PARAM_QUERY = "query"
    }
}
