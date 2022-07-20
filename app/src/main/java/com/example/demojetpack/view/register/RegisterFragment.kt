package com.example.demojetpack.view.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.demojetpack.databinding.FragmentRegisterBinding
import com.example.demojetpack.view.login.LogInFragmentDirections
import com.example.demojetpack.view.login.LoginState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment: Fragment() {
    private val registerViewModel: RegisterViewModel by viewModels()
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = registerViewModel

        initData()
        initView()
        observeData()
    }
    private fun initData(){

    }

    private fun observeData(){
        registerViewModel.registerState.observe(viewLifecycleOwner){ registerState ->
            when(registerState) {
                is RegisterState.NoState -> return@observe
                is RegisterState.Error ->
                    Toast.makeText(requireContext(), registerState.errorDesc, Toast.LENGTH_SHORT).show()
                is RegisterState.Success -> {
                    Toast.makeText(requireContext(), registerState.desc, Toast.LENGTH_SHORT).show()
                    requireView().findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLogInFragment())
                }
            }
            registerViewModel.resetRegisterState()
        }
    }

    private fun initView(){
        binding.buttonRegisterRegister.setOnClickListener {
            registerViewModel.register()
        }
    }
}