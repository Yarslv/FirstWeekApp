package com.internship.firstweekapp.util

class Scene(
    var scene_bg: String = "",
    var eff: Anim = Anim.NONE,
    val actions: ArrayList<BaseGameAction> = arrayListOf()
)