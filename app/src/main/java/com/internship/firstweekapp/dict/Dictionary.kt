package com.internship.firstweekapp.dict

import android.content.Context
import com.internship.firstweekapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

enum class State {
    PL_UK, UK_PL
}

class Dictionary(var context: Context) {
    private val wordMapPL = mutableMapOf<String, ArrayList<String>>()
    private val wordMapUA = mutableMapOf<String, ArrayList<String>>()
    private var state = State.PL_UK

    suspend fun read() {
        withContext(Dispatchers.IO) {
            val d = context.assets.open("slovnyk_pl-uk.xdxf").bufferedReader().use { it.readText() }
            val a = d.substring(d.indexOf("</description>") + 14)
                .split("<ar>")


            val keyPattern = "<k>[A-Za-z0-9 ]+<k>".toRegex()
            val valuePattern =
                "&quot;[ЙЦУКЕНГШЩЗХЇЄЖДЛОРПАВІФЯЧСМИТЬБЮҐґйцукенгшщзхїєждлорпавіфячсмитьбю0-9'\\- ]+&quot;</ar>".toRegex()
            val valuePattern2 =
                "[ЙЦУКЕНГШЩЗХЇЄЖДЛОРПАВІФЯЧСМИТЬБЮҐґйцукенгшщзхїєждлорпавіфячсмитьбю0-9'\\- ]+</ar>".toRegex()
            a.forEach { elem ->
                if (elem.contains("<k>")) {
                    val key = elem.substring(elem.indexOf("<k>") + 3, elem.indexOf("</k>"))

                    if (valuePattern.find(elem) != null) {
                        val value = valuePattern
                            .find(elem)!!.value.replace("&quot;", "").replace("</ar>", "")
                        if (wordMapPL.containsKey(key)) {
                            wordMapPL[key]!!.add(value.lowercase())
                        } else {
                            wordMapPL[key] = arrayListOf(value.lowercase())
                        }
                    } else {
                        val value = valuePattern2
                            .find(elem)!!.value.replace("</ar>", "")
                        if (wordMapPL.containsKey(key)) {
                            wordMapPL[key]!!.add(value.lowercase())
                        } else {
                            wordMapPL[key] = arrayListOf(value.lowercase())
                        }
                    }

                }

            }
        }
        withContext(Dispatchers.IO) {
            val d = context.assets.open("slovnyk_uk-pl.xdxf").bufferedReader().use { it.readText() }
            val a = d.substring(d.indexOf("</description>") + 14)
                .split("<ar>")


            val keyPattern =
                "<k>[ЙЦУКЕНГШЩЗХЇЄЖДЛОРПАВІФЯЧСМИТЬБЮҐґйцукенгшщзхїєждлорпавіфячсмитьбю0-9'\\- ]+<k>".toRegex()
            val valuePattern =
                "&quot;[AaĄąBbCcĆćDdEeĘęFfGgHhIiJjKkLlŁłMmNnŃńOoÓóPpRrSsŚśTtUuWwVvYyZzŹźŻżXx0-9_'%?!\\- ]+&quot;=?</ar>".toRegex()
            val valuePattern2 =
                "[AaĄąBbCcĆćDdEeĘęFfGgHhIiJjKkLlŁłMmNnŃńOoÓóPpRrSsŚśTtUuWwVvYyZzŹźŻżXx0-9_'!%?\\- ]+=?</ar>".toRegex()
            a.forEach { elem ->
                if (elem.contains("<k>")) {
                    val key = elem.substring(elem.indexOf("<k>") + 3, elem.indexOf("</k>"))

                    if (valuePattern.find(elem) != null) {
                        val value = valuePattern
                            .find(elem)!!.value.replace("&quot;", "").replace("</ar>", "")
                        if (wordMapUA.containsKey(key)) {
                            wordMapUA[key]!!.add(value.lowercase())
                        } else {
                            wordMapUA[key] = arrayListOf(value.lowercase())
                        }
                    } else {
                        val value = valuePattern2
                            .find(elem)!!.value.replace("</ar>", "")
                        if (wordMapUA.containsKey(key)) {
                            wordMapUA[key]!!.add(value.lowercase())
                        } else {
                            wordMapUA[key] = arrayListOf(value.lowercase())
                        }
                    }

                }

            }
        }
    }

    fun change() {
        state = if (state == State.PL_UK)
            State.UK_PL
        else
            State.PL_UK
    }

    fun get(word: String): ArrayList<String> {
        return when (state) {
            State.PL_UK -> (if (wordMapPL[word] == null) arrayListOf() else wordMapPL[word]) as ArrayList<String>
            State.UK_PL -> (if (wordMapUA[word] == null) arrayListOf() else wordMapUA[word]) as ArrayList<String>
        }
    }

    fun getDictLabel(): String {
        return when (state) {
            State.PL_UK -> context.getString(R.string.pl_to_ua)
            State.UK_PL -> context.getString(R.string.ua_to_pl)
        }
    }

}