package com.internship.firstweekapp.ui.fragments.baseScreenFragment

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.internship.firstweekapp.models.MultiAnswerQuestionModel
import com.internship.firstweekapp.models.Question
import com.internship.firstweekapp.models.SingleAnswerQuestionModel
import com.internship.firstweekapp.models.TextWritingAnswerQuestionModel
import com.internship.firstweekapp.util.Constants
import com.internship.firstweekapp.util.MyItemRecyclerViewAdapter
import org.json.JSONArray

class BaseScreenFragmentViewModel(private var jsonArr: JSONArray) : ViewModel() {

    var adapter: ObservableField<MyItemRecyclerViewAdapter> =
        ObservableField<MyItemRecyclerViewAdapter>()
    private val models = arrayListOf<Question>()
    private var _navigate = MutableLiveData(false)

    fun getNavigate(): LiveData<Boolean> {
        return _navigate
    }

    init {
        setAdapterByModelsFromJSONArray()
    }

    private fun setAdapterByModelsFromJSONArray() {
        for (i in 0 until jsonArr.length()) {
            when (jsonArr.getJSONObject(i).getString(Constants.TYPES_BLOCK_TAG)) {
                Constants.FIRST_SCREEN_TAG -> {
                    val k = jsonArr.getJSONObject(i).getJSONArray(Constants.ANSWERS_BLOCK_TAG)
                    val list = arrayListOf<String>()
                    for (j in 0 until k.length()) {
                        list.add(k.getString(j))
                    }

                    models.add(
                        i, SingleAnswerQuestionModel(
                            jsonArr.getJSONObject(i).getString(
                                Constants.QUESTIONS_BLOCK_TAG
                            ),
                            list
                        )
                    )
                }
                Constants.SECOND_SCREEN_TAG -> {
                    val k = jsonArr.getJSONObject(i).getJSONArray(Constants.ANSWERS_BLOCK_TAG)
                    val list = arrayListOf<String>()
                    for (j in 0 until k.length()) {
                        list.add(k.getString(j))
                    }
                    models.add(
                        i, MultiAnswerQuestionModel(
                            jsonArr.getJSONObject(i).getString(
                                Constants.QUESTIONS_BLOCK_TAG
                            ),
                            list
                        )
                    )
                }
                Constants.THIRD_SCREEN_TAG -> models.add(
                    i, TextWritingAnswerQuestionModel(
                        jsonArr.getJSONObject(i).getString(
                            Constants.QUESTIONS_BLOCK_TAG
                        )
                    )
                )
            }
        }
        adapter.set(MyItemRecyclerViewAdapter(models))
    }

    fun onClick() {
        var flag = true
        for (i in models) {
            if (!i.check()) {
                flag = false
            }
        }
        if (flag) {
            _navigate.value = true
            _navigate.value = false
        }
    }

    fun getUserAnswersArray(): Array<String> {
        val arrayList = arrayListOf<String>()
        for (i in models)
            arrayList.add(i.getUserAnswer())
        return arrayList.toTypedArray()
    }
}