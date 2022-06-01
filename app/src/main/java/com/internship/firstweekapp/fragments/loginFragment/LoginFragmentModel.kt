package com.internship.firstweekapp.fragments.loginFragment

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.internship.firstweekapp.BR

class LoginFragmentModel: BaseObservable() {
    private var login: ObservableField<String> = ObservableField("")
    private var password: ObservableField<String> = ObservableField("")
    private var loginError: ObservableField<String> = ObservableField("")
    private var passwordError: ObservableField<String> = ObservableField("")

    @Bindable
    fun getLoginError(): String{
        return loginError.get().toString()
    }
    fun setLoginError(value: String){
        loginError.set(value)
        notifyPropertyChanged(BR.loginError)
    }

    @Bindable
    fun getPasswordError(): String{
        return passwordError.get().toString()
    }
    fun setPasswordError(value: String){
        passwordError.set(value)
        notifyPropertyChanged(BR.passwordError)
    }

    @Bindable
    fun getLogin(): String{
        return login.get().toString()
    }

    fun setLogin(value: String){
        setLoginError("")
        login.set(value)
        notifyPropertyChanged(BR.login)
    }

    @Bindable
    fun getPassword(): String{
        return password.get().toString()
    }

    fun setPassword(value: String){
        setPasswordError("")
        password.set(value)
        notifyPropertyChanged(BR.password)
    }
}