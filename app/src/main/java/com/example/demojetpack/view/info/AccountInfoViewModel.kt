package com.example.demojetpack.view.info

import androidx.lifecycle.ViewModel
import com.example.demojetpack.repo.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountInfoViewModel @Inject constructor(
    private val repo: AppRepository
) : ViewModel() {
}