package com.internship.firstweekapp.ui.memes_list

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.paging.PagingData
import com.internship.firstweekapp.retrofit.Meme
import com.internship.firstweekapp.ui.memes_list.category_list.CategoryModel

class MemeListModel {
    var textFilter = ObservableField("")

    val items = ObservableField<PagingData<Meme>?>()
    val categoryItems = ObservableField(
        arrayListOf(
            CategoryModel( "All", "", true),
            CategoryModel( "GGG", "ggg", isCurrentlyUsed = false),
            CategoryModel( "Pepe", "pepe", isCurrentlyUsed = false)
        )
    )

    val isLoad = ObservableBoolean(false)
    val isError = ObservableBoolean(false)
    val error = ObservableField("")
}