package com.mercadolibre.mobile.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mercadolibre.mobile.R
import com.mercadolibre.mobile.databinding.FragmentDetailsBinding
import com.mercadolibre.mobile.utils.extensions.autoCleared
import com.mercadolibre.mobile.utils.extensions.setSharedElementTransitionOnEnter
import com.mercadolibre.mobile.utils.extensions.startEnterTransitionAfterLoadingImage
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSharedElementTransitionOnEnter()
        postponeEnterTransition()
        setupView()
    }

    private fun setupView() {

        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        with(args.product) {

            binding.image.transitionName = args.transitionName
            startEnterTransitionAfterLoadingImage(thumbnail.orEmpty(), binding.image)

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
