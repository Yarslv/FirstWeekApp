package com.internship.firstweekapp.fragments.loginFragment


import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.Navigation
import com.internship.firstweekapp.R

class LoginFragmentViewModel(application: Application) : AndroidViewModel(application) {

    val model = LoginFragmentModel()

    fun onClick(view: View) {
        checkLogin()
        checkPassword()
        if (isLoginCorrect() && isPasswordCorrect())
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_resultFragment)
    }

    private fun isLoginCorrect():Boolean{
        return model.getLoginError() == ""
    }
    private fun isPasswordCorrect():Boolean{
        return model.getPasswordError() == ""
    }

    private fun checkLogin() {
        with(getApplication<Application>()) {
            if (model.getLogin().isEmpty()) {
                model.setLoginError(getString(R.string.empty_error_text))
            } else if (model.getLogin().contains(" ")) {
                model.setLoginError(getString(R.string.spaces_error_text))
            }
        }
    }

    private fun checkPassword(){
        with(getApplication<Application>()) {
            if (model.getPassword().isEmpty()) {
                model.setPasswordError(getString(R.string.empty_error_text))
            } else if (model.getPassword().contains(" ")) {
                model.setPasswordError(getString(R.string.spaces_error_text))
            } else if (model.getPassword().length < 8) {
                model.setPasswordError(getString(R.string.password_length_error_text))
            } else if (!".?[a-z].?".toRegex().containsMatchIn(model.getPassword())) {
                model.setPasswordError(getString(R.string.small_letter_error_text))
            } else if (!".?[A-Z].?".toRegex().containsMatchIn(model.getPassword())) {
                model.setPasswordError(getString(R.string.big_letter_error_text))
            } else if (!".?[0-9].?".toRegex().containsMatchIn(model.getPassword())) {
                model.setPasswordError(getString(R.string.one_number_error_text))
            } else if (!".?[!@#$%^&*()?_=+:;`~.,].?".toRegex()
                    .containsMatchIn(model.getPassword())
            ) {
                model.setPasswordError(getString(R.string.one_symbol_error_text))
            }

        }
    }

}