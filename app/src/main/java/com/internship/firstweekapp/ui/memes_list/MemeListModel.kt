package com.internship.firstweekapp.ui.memes_list

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.paging.PagingData
import com.internship.firstweekapp.retrofit.Meme
import com.internship.firstweekapp.ui.memes_list.category_list.CategoryModel

class MemeListModel {
    var textFilter = ObservableField("")

    val isMock = ObservableBoolean(true)

    val items = ObservableField<PagingData<Meme>?>()
    val categoryItems = ObservableField(
        arrayListOf(
            CategoryModel("All"),
            CategoryModel("Wolf"),
            CategoryModel("GGG"),
            CategoryModel("Dogs"),
            CategoryModel("Pepe")
        )
    )

    val isLoad = ObservableBoolean(false)
    val isError = ObservableBoolean(false)
    val error = ObservableField("")
}