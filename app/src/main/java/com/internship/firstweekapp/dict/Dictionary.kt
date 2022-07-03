package com.internship.firstweekapp.dict

import android.content.Context
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R

enum class State {
    STANDARD, REVERSE
}

class Dictionary(var context: Context) {
    var state = State.STANDARD
    private val array = arrayListOf<DictItem>()
    var name: String = ""
    var from: String = ""
    var to: String = ""


    fun read() {
        val d =
            context.assets.open(Constants.DICTIONARY_NAME).bufferedReader().use { it.readText() }
        val a = d.substring(d.indexOf("</description>") + 14)
            .split("<ar>")

        name = "<full_name>.+</full_name>".toRegex().find(d)?.value.toString()
            .replace("<full_name>", "")
            .replace("</full_name>", "")

        from = "lang_from=\"[A-Z]+\"".toRegex().find(d)?.value.toString()
            .replace("lang_from=\"", "")
            .replace("\"", "")

        to = "lang_to=\"[A-Z]+\"".toRegex().find(d)?.value.toString()
            .replace("lang_to=\"", "")
            .replace("\"", "")


        a.forEach { elem ->
            if (elem.contains("<k>")) {
                val key = elem.substring(elem.indexOf("<k>") + 3, elem.indexOf("</k>"))
                val tvalue = elem.split("\n")
                val value = tvalue[tvalue.lastIndex - 1]
                    .replace("&quot", "")
                    .replace("</ar>", "")
                    .replace(";", "")
                    .trim()
                    .lowercase()
                array.add(DictItem(key, value.lowercase()))
            }

        }

    }
    fun change() {
        state = if (state == State.STANDARD)
            State.REVERSE
        else
            State.STANDARD
    }

    fun get(word: String): ArrayList<DictItem> {
        return when (state) {
            State.STANDARD -> (array.filter { i -> i.wordOrig == word }) as ArrayList<DictItem>
            State.REVERSE -> (array.filter { i -> i.wordTrans == word }) as ArrayList<DictItem>
        }
    }

    fun getDictLabel(): String {
        return when (state) {
            State.STANDARD -> context.getString(R.string.concate_two_to, from, to)
            State.REVERSE -> context.getString(R.string.concate_two_to, to, from)
        }
    }

}

data class DictItem(val wordOrig: String, val wordTrans: String)