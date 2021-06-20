package com.chahine.showhive.home.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.chahine.showhive.base.BaseFragment
import com.chahine.showhive.home.R
import com.chahine.showhive.home.databinding.FragmentRecyclerViewLoadingBinding
import com.chahine.showhive.home.util.LoadedValue
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {

    @Inject lateinit var profileAdapter: ProfileAdapter

    override fun getLayoutId() = R.layout.fragment_recycler_view_loading

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentRecyclerViewLoadingBinding.bind(view)

        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            adapter = profileAdapter
        }

        lifecycleScope.launch {
            viewModel.profileFlow.collectLatest {
                when (it) {
                    is LoadedValue.Loading -> binding.spinner.isVisible = true
                    is LoadedValue.Success -> {
                        binding.spinner.isVisible = false
                        profileAdapter.submitList(it.value)
                    }
                    is LoadedValue.Error -> {
                        binding.spinner.isVisible = false
                        Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        viewModel.fetchProfile()
    }
}
