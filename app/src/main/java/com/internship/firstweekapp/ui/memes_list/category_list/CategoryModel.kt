package com.internship.firstweekapp.ui.memes_list.category_list

data class CategoryModel (val name: String, val value: String = name, var isCurrentlyUsed: Boolean)