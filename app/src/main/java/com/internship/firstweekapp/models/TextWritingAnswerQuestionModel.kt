package com.internship.firstweekapp.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.internship.firstweekapp.BR

class TextWritingAnswerQuestionModel(override val question: String) : Question, BaseObservable() {
    private val _userAnswer: ObservableField<String> = ObservableField("")
    private val _infoMsg: ObservableField<String> = ObservableField<String>("")

    var userAnswr: String
        @Bindable
        get() = _userAnswer.get().toString()
        set(value) {
            _userAnswer.set(value)
            notifyPropertyChanged(BR.userAnswr)
        }

    var infoMsg: String
        @Bindable
        get() = _infoMsg.get().toString()
        set(value) {
            _infoMsg.set(value)
            notifyPropertyChanged(BR.infoMsg)
        }

    override fun check(): Boolean {
        if (userAnswr.isNotEmpty()) {
            hideMsg()
            return true
        }
        infoMsg = "Type your answer!"
        return false
    }

    override fun hideMsg() {
        infoMsg = ""
    }

    override fun getUserAnswer() = userAnswr
}