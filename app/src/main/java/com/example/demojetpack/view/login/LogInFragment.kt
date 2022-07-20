package com.example.demojetpack.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.demojetpack.databinding.FragmentLogInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment: Fragment() {
    private val logInViewModel: LogInViewModel by viewModels()
    private lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = logInViewModel

        initData()
        initView()
        observeData()
    }
    private fun initData(){

    }

    private fun observeData(){
        logInViewModel.loginState.observe(viewLifecycleOwner){ logicState ->
            when (logicState) {
                is LoginState.NoState -> return@observe
                is LoginState.Error ->
                    Toast.makeText(requireContext(), logicState.errorDesc, Toast.LENGTH_SHORT).show()
                is LoginState.Success ->
                    requireView().findNavController().navigate(
                        LogInFragmentDirections.actionLogInFragmentToAccountInfoFragment(logicState.account))
            }
            logInViewModel.setLoginStateDefault()
        }
    }

    private fun initView(){
        binding.buttonLoginLogin.setOnClickListener {
            logInViewModel.logIn()
        }

        binding.textViewLoginCreateAccount.setOnClickListener {
            requireView().findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToRegisterFragment())
        }
    }
}