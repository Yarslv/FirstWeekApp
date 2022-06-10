package com.internship.firstweekapp.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.internship.firstweekapp.BR

class MultiAnswerQuestionModel(
    override val question: String,
    override val answers: ArrayList<String>
) : QuestionWithAnswers, BaseObservable() {
    private val _infoMsg: ObservableField<String> = ObservableField<String>("")
    var infoMsg: String
        @Bindable
        get() = _infoMsg.get().toString()
        set(value) {
            _infoMsg.set(value)
            notifyPropertyChanged(BR.infoMsg)
        }
    val check1: ObservableField<Boolean> = ObservableField(false)
    val check2: ObservableField<Boolean> = ObservableField(false)
    val check3: ObservableField<Boolean> = ObservableField(false)
    val check4: ObservableField<Boolean> = ObservableField(false)

    override fun check(): Boolean {
        if (check1.get()!! || check2.get()!! || check3.get()!! || check4.get()!!) {
            hideMsg()
            return true
        }
        infoMsg = "Choose your variant!"
        return false
    }

    override fun hideMsg() {
        infoMsg = ""
    }

    override fun getUserAnswer(): String {
        val strBldr = StringBuilder()
        if (check1.get()!!)
            strBldr.append("1")
        if (check2.get()!!)
            strBldr.append("2")
        if (check3.get()!!)
            strBldr.append("3")
        if (check4.get()!!)
            strBldr.append("4")
        return strBldr.toString()
    }
}