package com.internship.firstweekapp.fragments.loginFragment


import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.internship.firstweekapp.R

class LoginFragmentViewModel: ViewModel() {
    val model = LoginFragmentModel()

    fun onClick(view: View){
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

    private fun checkLogin(){
        if(model.getLogin().isEmpty()){
            model.setLoginError("Can not be EMPTY")
        }else if(model.getLogin().indexOf(" ") != -1){
            model.setLoginError("Can not contain SPACES")
        }
    }

    private fun checkPassword(){
        if(model.getPassword().isEmpty()){
            model.setPasswordError("Can not be EMPTY")
        }else if(model.getPassword().indexOf(" ") != -1){
            model.setPasswordError("Can not contain SPACES")
        }else if (model.getPassword().length < 8){
            model.setPasswordError("Can not be less than 8 SYMBOLS")
        }else if (!".?[a-z].?".toRegex().containsMatchIn(model.getPassword())){
            model.setPasswordError("Must contains at least ONE SMALL LITERAL")
        }else if (!".?[A-Z].?".toRegex().containsMatchIn(model.getPassword())){
            model.setPasswordError("Must contains at least ONE BIG LITERAL")
        }else if (!".?[0-9].?".toRegex().containsMatchIn(model.getPassword())){
            model.setPasswordError("Must contains at least ONE NUMBER")
        }else if (!".?[!@#$%^&*()?_=+:;`~.,].?".toRegex().containsMatchIn(model.getPassword())){
            model.setPasswordError("Must contains at least ONE SYMBOL")
        }
    }
}