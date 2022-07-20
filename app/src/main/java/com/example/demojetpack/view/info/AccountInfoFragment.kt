package com.example.demojetpack.view.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.demojetpack.databinding.FragmentAccountInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountInfoFragment: Fragment() {
    private val accountInfoViewModel: AccountInfoViewModel by viewModels()
    private lateinit var binding: FragmentAccountInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = accountInfoViewModel
        val account = AccountInfoFragmentArgs.fromBundle(requireArguments()).account
        binding.account = account

        initData()
        initView()
        observeData()
    }

    private fun observeData() {

    }

    private fun initView() {
        binding.buttonAccountInfoLogout.setOnClickListener {
            requireView().findNavController().navigate(
                AccountInfoFragmentDirections.actionAccountInfoFragmentToLogInFragment())
        }
    }

    private fun initData() {

    }
}