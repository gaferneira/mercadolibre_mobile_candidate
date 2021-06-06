package com.mercadolibre.mobile.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mercadolibre.mobile.R
import com.mercadolibre.mobile.databinding.FragmentDetailsBinding
import com.mercadolibre.mobile.utils.GlideApp
import com.mercadolibre.mobile.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding by autoCleared()

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        setupView()
        return binding.root
    }

    private fun setupView() {

        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        with(args.product) {
            GlideApp.with(requireContext()).load(thumbnail).into(binding.image)

            binding.textViewTitle.text = title
            binding.labelSeller.isVisible = seller.isNotBlank()
            binding.textViewSeller.text = seller

            val numberFormat = NumberFormat.getCurrencyInstance();
            binding.textViewPrice.text = numberFormat.format(price)

            binding.buttonPurchase.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(permalink)
                })
            }
        }
    }

}
