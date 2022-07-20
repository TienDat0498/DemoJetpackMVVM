package com.example.demojetpack.view.login

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
class LogInViewModel @Inject constructor(
    private val repo: AppRepository
) : ViewModel() {

    private var _loginState: MutableLiveData<LoginState> = MutableLiveData(LoginState.NoState)
    val loginState: LiveData<LoginState>
        get() = _loginState

    fun setLoginStateDefault(){
        _loginState.value = LoginState.NoState
    }

    val accountInputText: MutableLiveData<String> = MutableLiveData("")
    val passwordInputText: MutableLiveData<String> = MutableLiveData("")

    fun logIn() {
        val acc = accountInputText.value
        val password = passwordInputText.value
        if (acc.isNullOrEmpty()) {
            _loginState.value = LoginState.Error("Please enter account")
            return
        } else if (password.isNullOrEmpty()) {
            _loginState.value = LoginState.Error("Please enter password")
            return
        }
        viewModelScope.launch {
            if (!repo.checkAccount(acc)){
                _loginState.value = LoginState.Error("Account does not exist")
                return@launch
            }
            val account = repo.loginAccount(acc, password)
            if (account == null){
                _loginState.value = LoginState.Error("Password incorrect")
                return@launch
            }
            _loginState.value = LoginState.Success(account)
        }
    }
}

sealed class LoginState {
    class Error(val errorDesc: String) : LoginState()
    class Success(val account: Account) : LoginState()
    object NoState : LoginState()
}