package com.example.demojetpack.view.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demojetpack.data.Account
import com.example.demojetpack.repo.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repo: AppRepository
) : ViewModel() {


    val account: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")
    val name: MutableLiveData<String> = MutableLiveData("")
    val phone: MutableLiveData<String> = MutableLiveData("")

    private val _registerState: MutableLiveData<RegisterState> = MutableLiveData(RegisterState.NoState)
    val registerState: LiveData<RegisterState>
        get() = _registerState

    fun resetRegisterState(){
        _registerState.value = RegisterState.NoState
    }

    fun register(){
        if (account.value.isNullOrEmpty()){
            _registerState.value = RegisterState.Error("Invalid account")
            return
        } else if (password.value.isNullOrEmpty()) {
            _registerState.value = RegisterState.Error("Invalid password")
            return
        } else if (name.value.isNullOrEmpty()) {
            _registerState.value = RegisterState.Error("Invalid name")
            return
        } else if (phone.value.isNullOrEmpty()) {
            _registerState.value = RegisterState.Error("Invalid phone number")
            return
        }
        viewModelScope.launch {
            if (repo.checkAccount(account.value!!)) {
                _registerState.value = RegisterState.Error("Account exists")
                return@launch
            }
            else {
                val newAccount = Account(account.value!!,
                password.value!!,
                name.value!!,
                phone.value!!)
                repo.registerAccount(newAccount)
                _registerState.value = RegisterState.Success("Account created")
            }
        }
    }
}

sealed class RegisterState {
    class Error(val errorDesc: String): RegisterState()
    class Success(val desc: String): RegisterState()
    object NoState: RegisterState()
}