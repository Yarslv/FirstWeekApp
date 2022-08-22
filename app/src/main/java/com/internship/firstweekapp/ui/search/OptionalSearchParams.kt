package com.internship.firstweekapp.ui.search

import com.internship.firstweekapp.Constants

enum class OptionalSearchParams(val param: String) {
    Genus("gen:${Constants.PLACEHOLDER} "),
    Subspecies("ssp:${Constants.PLACEHOLDER} "),
    BackgroundSpecies("also:${Constants.PLACEHOLDER} "),
    SoundType("type:${Constants.PLACEHOLDER} "),
    Location("loc:${Constants.PLACEHOLDER} "),
    Country("cnt:${Constants.PLACEHOLDER} "),
    Remarks("rmk:${Constants.PLACEHOLDER} "),
    Recordist("rec:${Constants.PLACEHOLDER} ")

}